package com.company.UzCard.service;

import com.company.UzCard.dto.request.CardDTO;
import com.company.UzCard.dto.request.UpdatePhoneDTO;
import com.company.UzCard.dto.response.CardBalanceDTO;
import com.company.UzCard.dto.response.ResponseCardDTO;
import com.company.UzCard.entity.CardEntity;
import com.company.UzCard.enums.EntityStatus;
import com.company.UzCard.exp.ItemNotFoundException;
import com.company.UzCard.mapper.CardBalanceMapper;
import com.company.UzCard.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;


@Service
@Slf4j
@RequiredArgsConstructor
public class CardService {
    private final CardRepository cardRepository;

    public ResponseCardDTO created(CardDTO dto) {
        CardEntity entity = new CardEntity();
        entity.setCardNumber(generateCardNumber());
        entity.setBalance(0L);
        entity.setClientId(dto.getClientId());
        entity.setPhone(dto.getPhone());
        entity.setStatus(EntityStatus.ACTIVE);
        String expDate = String.valueOf(LocalDateTime.now().plusYears(4));
        entity.setExpDate(expDate);
        cardRepository.save(entity);
        return toDTO(entity);
    }

    public List<ResponseCardDTO> getListByPhone(String phone) {
        List<CardEntity> cardEntities = cardRepository.findByPhoneAndStatus(phone, EntityStatus.ACTIVE);
        if (cardEntities.isEmpty()){
            return new LinkedList<>();
        }
        List<ResponseCardDTO> responseCardDTOS = new ArrayList<>();
        cardEntities.forEach(entity -> {
            responseCardDTOS.add(toDTO(entity));
        });
        return responseCardDTOS;
    }

    public List<ResponseCardDTO> getListByClientId(String clientId) {
        List<CardEntity> cardEntities = cardRepository.findByClientIdAndStatus(clientId, EntityStatus.ACTIVE);
        if (cardEntities.isEmpty()){
            return new LinkedList<>();
        }
        List<ResponseCardDTO> responseCardDTOS = new ArrayList<>();
        cardEntities.forEach(entity -> {
            responseCardDTOS.add(toDTO(entity));
        });
        return responseCardDTOS;
    }

    public ResponseCardDTO getByCardNumber(String cardNumber) {
        Optional<CardEntity> optional = cardRepository.findByCardNumberAndStatus(cardNumber, EntityStatus.ACTIVE);
        if (optional.isEmpty()){
            log.error("Card Not Found {} {}", cardNumber, CardService.class);
            throw new ItemNotFoundException("Card Not Found");
        }
        return toDTO(optional.get());
    }

    public CardBalanceDTO getBalanceByCardNumber(String cardNumber) {
        CardBalanceMapper mapper = cardRepository.getBalanceByCardNumber(cardNumber);
        if (mapper == null){
            log.error("Card Not Found {} {}", cardNumber, CardService.class);
            throw new ItemNotFoundException("Card Not Found");
        }
        CardBalanceDTO dto = new CardBalanceDTO();
        dto.setId(mapper.getId());
        dto.setBalance(mapper.getBalance());
        return dto;
    }

    public String  update(String cardId, EntityStatus status) {
        get(cardId);
        int m = cardRepository.updateStatus(status, cardId);
        if (m > 0){
            return "Success";
        } else return "Failed";
    }

    public CardEntity get(String cardId){
        Optional<CardEntity> optional = cardRepository.findByUuid(cardId);
        if (optional.isEmpty()){
            log.error("Card Not Found {}{}", cardId, CardService.class);
            throw new ItemNotFoundException("Card Not Found");
        }
        return optional.get();
    }

    public String  generateCardNumber(){
        StringBuilder builder = new StringBuilder();
        builder.append("8600");
        while (true){
            for (int i = 0; i < 12; i++) {
                int rand = (int)(Math.random() * 10) + 1;
                builder.append(rand);
            }
            Optional<CardEntity> optional = cardRepository.findByCardNumberAndStatus(builder.toString(), EntityStatus.ACTIVE);
            if (optional.isEmpty()){
                return builder.toString();
            }
        }
    }

    public ResponseCardDTO toDTO(CardEntity entity){
        ResponseCardDTO dto = new ResponseCardDTO();
        dto.setBalance(entity.getBalance());
        dto.setClientId(entity.getClientId());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setExpDate(entity.getExpDate());
        dto.setPhone(entity.getPhone());
        dto.setNumber(entity.getCardNumber());
        dto.setUuid(entity.getUuid());
        return dto;
    }


    public String  updatePhone(String cardId, UpdatePhoneDTO dto) {
        get(cardId);
        int m = cardRepository.updatePhone(dto.getPhone(), cardId);
        if (m > 0){
            return "Success";
        } else return "Failed";
    }


}
