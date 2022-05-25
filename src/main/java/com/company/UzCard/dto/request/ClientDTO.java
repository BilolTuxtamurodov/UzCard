package com.company.UzCard.dto.request;

import com.company.UzCard.enums.EntityStatus;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class ClientDTO implements Serializable {
    private final String uuid;
    private final LocalDateTime createdDate;
    private final String name;
    private final String surname;
    private final String phone;
    private final EntityStatus status;
}
