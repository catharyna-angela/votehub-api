package com.octalsystems.votehub.v1.service;

import com.octalsystems.votehub.v1.entity.Client;
import com.octalsystems.votehub.v1.entity.Voting;
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
        log.info("'Cliente criado.'");

        return client;
    }

    @Transactional
    public void update(Long id, Client updateClientDTO) { //extrair o id do cliente pelo token e sobrescrever os dados do DTO para os dados do cliente no banco.
        try {
            Client existingClient = clientRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Id do cliente está incorreto ou não existe."));

            existingClient.setName(updateClientDTO.getName());
            existingClient.setEmail(updateClientDTO.getEmail());

            clientRepository.save(existingClient);
            log.info("'Cliente atualizado com sucesso.'");

        } catch (Exception ex) {
            log.error("'Não foi possível atualizar o cliente: {}'", ex.getMessage());
            throw ex;
        }
    }

}
