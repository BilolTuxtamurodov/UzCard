package com.company.UzCard.entity;

import com.company.UzCard.enums.EntityStatus;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
public class BaseEntity {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(name = "id")
    private String uuid;
    @Column
    private boolean visible;
    @Column
    private LocalDateTime createdDate = LocalDateTime.now();
}
