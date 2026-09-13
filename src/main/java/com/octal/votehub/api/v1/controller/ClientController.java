package com.octal.votehub.api.v1.controller;

import com.octal.votehub.api.v1.domain.entity.Client;
import com.octal.votehub.api.v1.dto.client.CreateClientDTO;
import com.octal.votehub.api.v1.dto.client.ResponseClientDTO;
import com.octal.votehub.api.v1.dto.client.UpdateClientDTO;
import com.octal.votehub.api.v1.mapper.ClientMapper;
import com.octal.votehub.api.v1.security.jwt.UserDetailsImpl;
import com.octal.votehub.api.v1.service.ClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/clients")
public class ClientController {

    private final ClientService clientService;

    @PostMapping
    ResponseEntity<ResponseClientDTO> create(@Valid @RequestBody CreateClientDTO createClientDTO){
        Client client = clientService.save(ClientMapper.toClient(createClientDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(ClientMapper.toResponseClientDTO(client));
    }

    @PatchMapping
    ResponseEntity<Void> updateAccountInfo(@Valid @RequestBody UpdateClientDTO updateClientDTO,
                                @AuthenticationPrincipal UserDetailsImpl userDetails){
        clientService.updateAccountInfo(ClientMapper.toClient(updateClientDTO), userDetails.getUsername());
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping
    ResponseEntity<ResponseClientDTO> getMyAccountInfo(@AuthenticationPrincipal UserDetailsImpl userDetails){
        Client client = clientService.getMyAccountInfo(userDetails.getId());
        return ResponseEntity.status(HttpStatus.OK).body(ClientMapper.toResponseClientDTO(client));
    }

    //updatePassword
}
