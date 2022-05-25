package com.company.UzCard.entity;

import com.company.UzCard.enums.EntityStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "client")
public class ClientEntity extends BaseEntity {
    @Column
    private String name;
    @Column
    private String surname;
    @Column
    private String phone;

    @Column
    @Enumerated(EnumType.STRING)
    private EntityStatus status;
}
