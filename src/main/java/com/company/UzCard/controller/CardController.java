package com.company.UzCard.controller;

import com.company.UzCard.dto.request.CardDTO;
import com.company.UzCard.dto.request.UpdatePhoneDTO;
import com.company.UzCard.dto.response.CardBalanceDTO;
import com.company.UzCard.dto.response.ResponseCardDTO;
import com.company.UzCard.enums.EntityStatus;
import com.company.UzCard.service.CardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/card")
@Api(tags = "Card")
@Slf4j
@RequiredArgsConstructor
public class CardController  {

    private final CardService cardService;

    @ApiOperation(value = "Create", notes = "Method used for create card", nickname = "Bilol")
    @PostMapping("/create")
    public ResponseEntity<ResponseCardDTO> create(@RequestBody @Valid CardDTO dto){
        return ResponseEntity.ok(cardService.create(dto));
    }

    @ApiOperation(value = "Update", notes = "Method used for change card status", nickname = "Bilol")
    @PutMapping("/update/{cardId}")
    public ResponseEntity<String> update(@PathVariable("cardId") String cardId,
                                                  @RequestBody EntityStatus status){
        return ResponseEntity.ok(cardService.update(cardId, status));
    }

    @ApiOperation(value = "Assign Phone", notes = "Method used for assign phone", nickname = "Bilol")
    @PutMapping("/assign/{cardId}")
    public ResponseEntity<String> updatePhone(@PathVariable("cardId") String cardId,
                                              @RequestBody UpdatePhoneDTO dto){
        return ResponseEntity.ok(cardService.updatePhone(cardId, dto));
    }

    @ApiOperation(value = "Get Card List by phone", notes = "Method used get card list by phone  (for any)", nickname = "Bilol")
    @GetMapping("/list/{phone}")
    public ResponseEntity<List<ResponseCardDTO>> getListByPhone(@PathVariable("phone") String phone){
        return ResponseEntity.ok(cardService.getListByPhone(phone));
    }

    @ApiOperation(value = "Get Card List by client id", notes = "Method used get card list by client id", nickname = "Bilol")
    @GetMapping("/list/client/{clientId}")
    public ResponseEntity<List<ResponseCardDTO>> getListByClientId(@PathVariable("clientId") String clientId){
        return ResponseEntity.ok(cardService.getListByClientId(clientId));
    }

    @ApiOperation(value = "Get Card List by card Number ", notes = "Method used get card list by card number", nickname = "Bilol")
    @GetMapping("/card/{cardNumber}")
    public ResponseEntity<ResponseCardDTO> getByCardNumber(@PathVariable("cardNumber") String cardNumber){
        return ResponseEntity.ok(cardService.getByCardNumber(cardNumber));
    }

    @ApiOperation(value = "Get Card List by card Number ", notes = "Method used get card list by card number", nickname = "Bilol")
    @GetMapping("/card/balanse{cardNumber}")
    public ResponseEntity<CardBalanceDTO> getBalanceByCardNumber(@PathVariable("cardNumber") String cardNumber){
        return ResponseEntity.ok(cardService.getBalanceByCardNumber(cardNumber));
    }

}
