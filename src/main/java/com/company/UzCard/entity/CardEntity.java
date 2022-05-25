package com.company.UzCard.entity;

import com.company.UzCard.enums.EntityStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "card")
public class CardEntity extends BaseEntity{
    @Column
    private String cardNumber;
    @Column
    private String expDate;
    @Column
    private Long balance;

    @Column
    @Enumerated(EnumType.STRING)
    private EntityStatus status;

    @Column(name = "client_id", nullable = false)
    private String clientId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", insertable = false, updatable = false)
    private ClientEntity client;

    @Column
    private String phone;
}
