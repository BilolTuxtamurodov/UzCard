package com.company.UzCard.controller;

import com.company.UzCard.dto.request.CardDTO;
import com.company.UzCard.dto.response.ResponseCardDTO;
import com.company.UzCard.service.CardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/card")
@Api(tags = "Card")
@Slf4j
@RequiredArgsConstructor
public class CardController  {

    private final CardService cardService;

    @ApiOperation(value = "Create", notes = "Method used for create card")
    @PostMapping("/create")
    public ResponseEntity<ResponseCardDTO> created(@RequestBody @Valid CardDTO dto){
        return ResponseEntity.ok(cardService.created(dto));
    }
}
