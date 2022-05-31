package com.company.UzCard.service;

import com.company.UzCard.dto.request.TransactionDTO;
import com.company.UzCard.dto.response.ResponseCardDTO;
import com.company.UzCard.dto.response.ResponseTransactionDTO;
import com.company.UzCard.entity.CardEntity;
import com.company.UzCard.entity.TransactionEntity;
import com.company.UzCard.enums.TransactionStatus;
import com.company.UzCard.exp.NotenoughMoneyException;
import com.company.UzCard.mapper.CardBalanceMapper;
import com.company.UzCard.repository.CardRepository;
import com.company.UzCard.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private CardService cardService;
    @Autowired
    private CardRepository cardRepository;

    @Transactional
    public ResponseTransactionDTO create(TransactionDTO dto) {
       CardEntity toCard = cardService.getByCardNumberV2(dto.getToCard());
       CardEntity fromCard = cardService.getByCardNumberV2(dto.getFromCard());
       CardBalanceMapper fromCardAmount = cardRepository.getBalanceByCardNumber(fromCard.getCardNumber());
       TransactionEntity entity = new TransactionEntity();
       if (fromCardAmount.getBalance() < dto.getAmount()){
           throw new NotenoughMoneyException("Not enought money");
       }
       int m = cardRepository.updateAmount((fromCard.getBalance() - dto.getAmount()), fromCard.getCardNumber());
       if (m > 0){
          int n = cardRepository.updateAmount((toCard.getBalance() + dto.getAmount()), toCard.getCardNumber());
          if (n > 0){
              entity.setStatus(TransactionStatus.SUCCESS);
          } else {
              entity.setStatus(TransactionStatus.FAILED);
          }
       }
       entity.setAmount(dto.getAmount());
       entity.setFromCard(dto.getFromCard());
       entity.setToCard(dto.getToCard());
       entity.setVisible(true);
       transactionRepository.save(entity);
       return toDTO(entity);
    }

    public PageImpl<ResponseTransactionDTO> getByCardId(String cardId, int page, int size) {
        CardEntity card = cardService.get(cardId);

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC , "createdDate"));
        Page<TransactionEntity> pagination = transactionRepository.getByCard(card.getCardNumber(), pageable);
        List<ResponseTransactionDTO> transactionDTOS = new ArrayList<>();
        pagination.stream().forEach(entity -> {
            transactionDTOS.add(toDTO(entity));
        });

        return new PageImpl<>(transactionDTOS, pageable, pagination.getTotalElements());
    }

    public PageImpl<ResponseTransactionDTO> getByClientId(String clientId, int page, int size) {
        List<ResponseCardDTO> cardEntities = cardService.getListByClientId(clientId);
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC , "createdDate"));
        List<ResponseTransactionDTO> transactionDTOS = new ArrayList<>();
        int totalElements = 0;
        for (ResponseCardDTO cardDTO : cardEntities){
            Page<TransactionEntity>  pagenation = transactionRepository.getByCard(cardDTO.getNumber(), pageable);
            pagenation.stream().forEach(entity -> {
                transactionDTOS.add(toDTO(entity));
            });
            totalElements+=pagenation.getTotalElements();
        }
        return new PageImpl<>(transactionDTOS, pageable, totalElements);
    }

    public PageImpl<ResponseTransactionDTO> getByPhone(String phone, int page, int size) {
        List<ResponseCardDTO> cardEntities = cardService.getListByPhone(phone);
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC , "createdDate"));
        List<ResponseTransactionDTO> transactionDTOS = new ArrayList<>();
        int totalElements = 0;
        for (ResponseCardDTO cardDTO : cardEntities){
            Page<TransactionEntity>  pagenation = transactionRepository.getByCard(cardDTO.getNumber(), pageable);
            pagenation.stream().forEach(entity -> {
                transactionDTOS.add(toDTO(entity));
            });
            totalElements+=pagenation.getTotalElements();
        }
        return new PageImpl<>(transactionDTOS, pageable, totalElements);

    }

    /*public PageImpl<ResponseTransactionDTO> getByProfileName(String name, int page, int size) {
        return null;
    }*/

    public ResponseTransactionDTO toDTO(TransactionEntity entity){
        ResponseTransactionDTO dto = new ResponseTransactionDTO();
        dto.setId(entity.getUuid());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setToCard(entity.getToCard());
        dto.setFromCard(entity.getFromCard());
        dto.setAmount(entity.getAmount());
        dto.setProfileId(entity.getProfileId());
        dto.setStatus(entity.getStatus());
        return dto;
    }
}
