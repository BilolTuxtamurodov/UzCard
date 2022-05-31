package com.company.UzCard.controller;

import com.company.UzCard.dto.request.TransactionDTO;
import com.company.UzCard.dto.response.ResponseTransactionDTO;
import com.company.UzCard.service.TransactionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping("/transaction")
@Slf4j
@Api(tags = "Transaction")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;

    @ApiOperation(value = "Create", notes = "Method used for create", nickname = "Bilol")
    @PostMapping("/")
    public ResponseEntity<ResponseTransactionDTO> create(@RequestBody TransactionDTO dto){
        return ResponseEntity.ok(transactionService.create(dto));
    }

    @ApiOperation(value = "Get by cardId", notes = "Method used for get transaction by cardId", nickname = "Bilol")
    @RolesAllowed("'profile', 'bank'")
    @GetMapping("/card/{cardId}")
    public ResponseEntity<PageImpl<ResponseTransactionDTO>> getByCardId(@PathVariable("cardId") String cardId,
                                                                        @RequestParam(value = "page", defaultValue = "0") int page,
                                                                        @RequestParam(value = "size", defaultValue = "5") int size){
        return ResponseEntity.ok(transactionService.getByCardId(cardId, page, size));
    }

    @ApiOperation(value = "Get by clientId", notes = "Method used for get transaction by cardId", nickname = "Bilol")
    @RolesAllowed("'client', 'bank'")
    @GetMapping("/client{clientId}")
    public ResponseEntity<PageImpl<ResponseTransactionDTO>> getByClientId(@PathVariable("clientId") String cardId,
                                                                        @RequestParam(value = "page", defaultValue = "0") int page,
                                                                        @RequestParam(value = "size", defaultValue = "5") int size){
        return ResponseEntity.ok(transactionService.getByClientId(cardId, page, size));
    }

    @ApiOperation(value = "Get by phone", notes = "Method used for get transaction by phone", nickname = "Bilol")
    @RolesAllowed("'client', 'bank'")
    @GetMapping("/phone{phone}")
    public ResponseEntity<PageImpl<ResponseTransactionDTO>> getByPhone(@PathVariable("phone") String phone,
                                                                          @RequestParam(value = "page", defaultValue = "0") int page,
                                                                          @RequestParam(value = "size", defaultValue = "5") int size){
        return ResponseEntity.ok(transactionService.getByPhone(phone, page, size));
    }

   /* @ApiOperation(value = "Get by profile name", notes = "Method used for get transaction by profile name", nickname = "Bilol")
    @RolesAllowed("'client', 'bank'")
    @GetMapping("/profile_name{name}")
    public ResponseEntity<PageImpl<ResponseTransactionDTO>> getByProfileName(@PathVariable("name") String name,
                                                                       @RequestParam(value = "page", defaultValue = "0") int page,
                                                                       @RequestParam(value = "size", defaultValue = "5") int size){
        return ResponseEntity.ok(transactionService.getByProfileName(name, page, size));
    }*/


}
