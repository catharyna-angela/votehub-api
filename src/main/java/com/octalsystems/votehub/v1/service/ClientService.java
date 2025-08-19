package com.octalsystems.votehub.v1.service;

import com.octalsystems.votehub.v1.entity.Client;
import com.octalsystems.votehub.v1.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    @Transactional
    public Client save(Client client) {
        clientRepository.save(client);
        log.info("Cliente criado.");

        return client;
    }
}
