package com.company.UzCard.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "card")
public class CardEntity extends BaseEntity{
    @Column
    private String number;
    @Column
    private String expDate;
    @Column
    private Long balance;

    @Column(name = "client_id", nullable = false)
    private String clientId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private ClientEntity client;

    @Column
    private String phone;
}
