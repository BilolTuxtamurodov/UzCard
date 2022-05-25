package com.company.UzCard.service;

import com.company.UzCard.dto.request.CardDTO;
import com.company.UzCard.dto.response.ResponseCardDTO;
import com.company.UzCard.entity.CardEntity;
import com.company.UzCard.enums.EntityStatus;
import com.company.UzCard.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;


@Service
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
}
