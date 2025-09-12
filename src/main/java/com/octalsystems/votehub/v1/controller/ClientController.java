package com.octalsystems.votehub.v1.controller;

import com.octalsystems.votehub.v1.dto.CreateClientDTO;
import com.octalsystems.votehub.v1.dto.ResponseClientDTO;
import com.octalsystems.votehub.v1.dto.mapper.ClientMapper;
import com.octalsystems.votehub.v1.entity.Client;
import com.octalsystems.votehub.v1.service.ClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/clients")
public class ClientController {

    private final ClientService clientService;

    @PostMapping
    ResponseEntity<ResponseClientDTO> create(@Valid @RequestBody CreateClientDTO createClientDTO){
        Client client = clientService.save(ClientMapper.toClient(createClientDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(ClientMapper.toCreateResponseClientDTO(client));
    }
}
