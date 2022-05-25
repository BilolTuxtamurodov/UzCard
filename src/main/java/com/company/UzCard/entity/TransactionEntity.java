package com.company.UzCard.entity;

import com.company.UzCard.enums.TransactionStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "transations")
public class TransactionEntity extends BaseEntity{
    @Column
    private String fromCard;
    @Column
    private String toCard;
    @Column
    private Long amount;
    @Column
    @Enumerated(EnumType.STRING)
    private TransactionStatus status;
    @Column
    private String profileId;
}
