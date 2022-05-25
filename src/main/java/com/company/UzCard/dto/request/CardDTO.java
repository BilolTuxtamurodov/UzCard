package com.company.UzCard.dto.request;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class CardDTO {
    private String uuid;
    private LocalDateTime createdDate;
    private String number;
    private String expDate;
    private Long balance;
    private String clientId;
    private String phone;
}
