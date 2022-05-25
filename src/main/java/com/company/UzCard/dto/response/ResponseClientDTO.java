package com.company.UzCard.dto.response;

import com.company.UzCard.enums.EntityStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class ResponseClientDTO {
    private String uuid;
    private LocalDateTime createdDate;
    private String name;
    private String surname;
    private String phone;
    private EntityStatus status;
}
