package com.company.UzCard.dto.request;

import com.company.UzCard.enums.TransactionStatus;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class TransactionDTO {
    private String uuid;
    private LocalDateTime createdDate;
    private String fromCard;
    private String toCard;
    private Long amount;
    private TransactionStatus status;
    private String profileId;
}
