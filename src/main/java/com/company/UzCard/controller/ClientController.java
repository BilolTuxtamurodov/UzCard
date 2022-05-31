package com.company.UzCard.controller;

import com.company.UzCard.dto.request.ClientDTO;
import com.company.UzCard.dto.request.UpdateClientDTO;
import com.company.UzCard.dto.request.UpdatePhoneDTO;
import com.company.UzCard.dto.response.ResponseClientDTO;
import com.company.UzCard.enums.EntityStatus;
import com.company.UzCard.service.ClientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/client")
@Slf4j
@RequiredArgsConstructor
@Api(tags = "Client")
public class ClientController  {
    private final ClientService clientService;

    @ApiOperation(value = "Create", notes = "Method used for create client", nickname = "Bilol")
    @PostMapping("/create")
    public ResponseEntity<ResponseClientDTO> create(@RequestBody @Valid ClientDTO dto){
        return ResponseEntity.ok(clientService.create(dto));
    }

    @ApiOperation(value = "Update", notes = "Method used for update client (except phone)", nickname = "Bilol")
    @PutMapping("/update/{clientId}")
    public ResponseEntity<String> update(@PathVariable("clientId") String clientId,
                                                    @RequestBody UpdateClientDTO dto){
        return ResponseEntity.ok(clientService.update(clientId, dto));
    }

    @ApiOperation(value = "Change client status", notes = "Method used for change client status", nickname = "Bilol")
    @PutMapping("/update/status{clientId}")
    public ResponseEntity<String> updateStatus(@PathVariable("clientId") String clientId,
                                                    @RequestParam EntityStatus status){
        return ResponseEntity.ok(clientService.updateStatus(clientId, status));
    }

    @ApiOperation(value = "Change client phone", notes = "Method used for change client phone", nickname = "Bilol")
    @PutMapping("/update/phone{clientId}")
    public ResponseEntity<String> updatePhone(@PathVariable("clientId") String clientId,
                                                          @RequestBody UpdatePhoneDTO dto){
        return ResponseEntity.ok(clientService.updatePhone(clientId, dto));
    }

    @ApiOperation(value = "Client Pagination", notes = "Method used for get client pagination", nickname = "Bilol")
    @GetMapping("/getPagination")
    public ResponseEntity<PageImpl<ResponseClientDTO>> getPagination(@RequestParam(value = "page", defaultValue = "0") int page,
                                                                     @RequestParam(value = "size", defaultValue = "5") int size){
        return ResponseEntity.ok(clientService.getPagination(page, size));
    }

    @ApiOperation(value = "Get By Id", notes = "Method used for get by id client", nickname = "Bilol")
    @GetMapping("/get/{clientId}")
    public ResponseEntity<ResponseClientDTO> getById(@PathVariable("clientId") String clientId){
        return ResponseEntity.ok(clientService.getById(clientId));
    }

    @ApiOperation(value = "Get Profile Client List", notes = "Method used for get profile clients list with pagination", nickname = "Bilol")
    @GetMapping("/get/profile/")
    public ResponseEntity<PageImpl<ResponseClientDTO>> getList(
                                                          @RequestParam(value = "page", defaultValue = "0") int page,
                                                          @RequestParam(value = "size", defaultValue = "10") int size
                                                          ){
        return ResponseEntity.ok(clientService.getList(page, size));
    }


}
