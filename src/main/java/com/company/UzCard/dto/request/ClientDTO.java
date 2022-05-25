package com.company.UzCard.dto.request;

import com.company.UzCard.enums.EntityStatus;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class ClientDTO {
    private String name;
    private String surname;
    private String phone;
    private EntityStatus status;
}
