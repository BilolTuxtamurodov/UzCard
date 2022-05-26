package com.company.UzCard.service;

import com.company.UzCard.dto.request.ClientDTO;
import com.company.UzCard.dto.request.UpdateClientDTO;
import com.company.UzCard.dto.request.UpdatePhoneDTO;
import com.company.UzCard.dto.response.ResponseClientDTO;
import com.company.UzCard.entity.ClientEntity;
import com.company.UzCard.enums.EntityStatus;
import com.company.UzCard.exp.ItemAlreadyExistsException;
import com.company.UzCard.exp.ItemNotFoundException;
import com.company.UzCard.repository.ClientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    public ResponseClientDTO create(ClientDTO dto) {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        String profileUserName = authentication.getName();
        Optional<ClientEntity> optional = clientRepository.findByPhoneAndStatus(dto.getPhone(), EntityStatus.ACTIVE);
        if (optional.isPresent()){
            log.error("Phone Already exists {} {}", dto.getPhone(), ClientService.class);
            throw new ItemAlreadyExistsException("Phone Already exists");
        }
        ClientEntity entity = new ClientEntity();
        entity.setName(dto.getName());
        entity.setPhone(dto.getPhone());
        entity.setSurname(dto.getSurname());
        entity.setProfileUserName(profileUserName);
        entity.setStatus(EntityStatus.ACTIVE);
        clientRepository.save(entity);
        return toDTO(entity);
    }

    public String update(String clientId, UpdateClientDTO dto) {
        get(clientId);
        int m = clientRepository.update(dto.getName(), dto.getSurname(), clientId);
        if (m > 0){
            return "Success";
        } else return "Failed";
    }

    public String updateStatus(String clientId, EntityStatus status) {
        get(clientId);
        int m = clientRepository.updateStatus(status, clientId);
        if (m > 0){
            return "Success";
        } else return "Failed";
    }

    public String updatePhone(String clientId, UpdatePhoneDTO dto) {
        get(clientId);
        int m = clientRepository.updatePhone(dto.getPhone(), clientId);
        if (m > 0){
            return "Success";
        } else return "Failed";
    }

    public PageImpl<ResponseClientDTO> getPagination(int page, int size) {
        Pageable pageable = PageRequest.of(page,size, Sort.by(Sort.Direction.DESC, "createdDate"));
        List<ResponseClientDTO> responseClientDTOS = new ArrayList<>();
        Page<ClientEntity> clientEntities = clientRepository.findAll(pageable);

        clientEntities.stream().forEach(entity -> {
            responseClientDTOS.add(toDTO(entity));
        });
        return new PageImpl<ResponseClientDTO>(responseClientDTOS, pageable, clientEntities.getTotalElements());
    }

    public ResponseClientDTO getById(String clientId) {
        return toDTO(get(clientId));
    }

    public PageImpl<ResponseClientDTO> getList(int page, int size) {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        String profileUserName = authentication.getName();
        List<ResponseClientDTO> responseClientDTOS = new ArrayList<>();
        Pageable pageable = PageRequest.of(page,size,Sort.by(Sort.Direction.DESC, "createdDate"));
        Page<ClientEntity> clientEntities = clientRepository.findByProfileUserName(profileUserName, pageable);
        clientEntities.stream().forEach(entity -> {
            responseClientDTOS.add(toDTO(entity));
        });
        return new PageImpl<ResponseClientDTO>(responseClientDTOS, pageable, clientEntities.getTotalElements());
    }

    public ClientEntity get(String clientId){
        Optional<ClientEntity> optional = clientRepository.findById(clientId);
        if (optional.isEmpty()){
            log.error("Client Not Found {} {}", clientId, ClientService.class);
            throw new ItemNotFoundException("Client Not Found");
        }
        return optional.get();
    }

    public ResponseClientDTO toDTO(ClientEntity entity){
        ResponseClientDTO dto = new ResponseClientDTO();
        dto.setId(entity.getUuid());
        dto.setName(entity.getName());
        dto.setPhone(entity.getPhone());
        dto.setSurname(entity.getSurname());
        dto.setStatus(entity.getStatus());
        return dto;
    }
}
