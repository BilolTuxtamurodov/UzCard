package com.company.UzCard.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class ResponseCardDTO {
    private String uuid;
    private LocalDateTime createdDate;
    private String number;
    private String expDate;
    private Long balance;
    private String clientId;
    private String phone;
}
