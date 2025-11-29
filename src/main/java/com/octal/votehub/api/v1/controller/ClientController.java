package com.octal.votehub.api.v1.controller;

import com.octal.votehub.api.v1.dto.client.CreateClientDTO;
import com.octal.votehub.api.v1.dto.client.ResponseCreateClientDTO;
import com.octal.votehub.api.v1.dto.client.UpdateClientDTO;
import com.octal.votehub.api.v1.dto.mapper.ClientMapper;
import com.octal.votehub.api.v1.entity.Client;
import com.octal.votehub.api.v1.jwt.UserDetailsImpl;
import com.octal.votehub.api.v1.service.ClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/clients")
public class ClientController {

    private final ClientService clientService;

    @PostMapping
    ResponseEntity<ResponseCreateClientDTO> create(@Valid @RequestBody CreateClientDTO createClientDTO){
        Client client = clientService.save(ClientMapper.toClient(createClientDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(ClientMapper.toResponseCreateClientDTO(client));
    }

    @PatchMapping
    @PreAuthorize("hasRole('CLIENT')")
    ResponseEntity<Void> update(@Valid @RequestBody UpdateClientDTO updateClientDTO,
                                @AuthenticationPrincipal UserDetailsImpl userDetails){
        clientService.update(ClientMapper.toClient(updateClientDTO), userDetails.getUsername());
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
