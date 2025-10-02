package com.octalsystems.votehub.v1.controller;

import com.octalsystems.votehub.v1.dto.CreateClientDTO;
import com.octalsystems.votehub.v1.dto.ResponseCreateClientDTO;
import com.octalsystems.votehub.v1.dto.UpdateClientDTO;
import com.octalsystems.votehub.v1.dto.mapper.ClientMapper;
import com.octalsystems.votehub.v1.entity.Client;
import com.octalsystems.votehub.v1.service.ClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('CLIENT') AND #id == authentication.principal.id")
    ResponseEntity<Void> update(@PathVariable Long id, @Valid @RequestBody UpdateClientDTO updateClientDTO){
        clientService.update(id, ClientMapper.toClient(updateClientDTO));
        return ResponseEntity.status(HttpStatus.OK).build(); //passar como resposta um feedback de que a conta foi atualizada.
    }

}
