package com.octalsystems.votehub.v1.controller;

import com.octalsystems.votehub.v1.entity.Client;
import com.octalsystems.votehub.v1.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/clients")
public class ClientController {

    private final ClientService clientService;

    @PostMapping
    ResponseEntity<Client> create(@RequestBody Client client){
        Client client1 = clientService.save(client);
        return ResponseEntity.status(HttpStatus.CREATED).body(client1);
    }
}
