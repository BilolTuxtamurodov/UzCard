package com.company.UzCard.dto;

import com.company.UzCard.enums.EntityStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CardFilterDTO {
    private String clientId;
    private String cardNumber;
    private String cardId;
    private String fromCard;
    private String fromBalance;
    private String toBalance;
    private LocalDateTime createdDate;
    private String profileName;
    private EntityStatus status;
}
