package com.company.UzCard.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UpdateClientDTO {
    @NotBlank
    private String name;
    @NotBlank
    private String surname;
}
