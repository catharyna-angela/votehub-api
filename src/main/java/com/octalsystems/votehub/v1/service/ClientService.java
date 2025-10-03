package com.octalsystems.votehub.v1.service;

import com.octalsystems.votehub.v1.entity.Client;
import com.octalsystems.votehub.v1.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Client save(Client client) {
        Optional<Client> emailExists = clientRepository.findByEmail(client.getEmail());

        if (emailExists.isPresent()) {
            throw new IllegalArgumentException("'Usuário já existe com o mesmo e-mail.'");
        }

        client.setPassword(passwordEncoder.encode(client.getPassword()));
        clientRepository.save(client);
        log.info("'Cliente criado.'");

        return client;
    }

    @Transactional
    public void update(Long id, Client updateClientDTO) {
        try {
            Client existingClient = clientRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("'Id do cliente está incorreto ou não existe.'"));

            existingClient.setName(updateClientDTO.getName());
            existingClient.setEmail(updateClientDTO.getEmail());

            clientRepository.save(existingClient);
            log.info("'Cliente atualizado com sucesso.'");

        } catch (Exception ex) {
            log.error("'Não foi possível atualizar o nome ou e-mail do cliente: {}'", ex.getMessage());
            throw ex;
        }
    }

}
