package com.company.UzCard.dto.response;

import com.company.UzCard.enums.TransactionStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class ResponseTransactionDTO {
    private String uuid;
    private LocalDateTime createdDate;
    private String fromCard;
    private String toCard;
    private Long amount;
    private TransactionStatus status;
    private String profileId;
}
