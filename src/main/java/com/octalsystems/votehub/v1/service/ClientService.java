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
    private final EmailService emailService;

    @Transactional
    public Client save(Client client) {
        Optional<Client> emailExists = clientRepository.findByEmail(client.getEmail());

        if (emailExists.isPresent()) {
            throw new IllegalArgumentException("'Usuário já existe com o mesmo e-mail.'");
        }

        client.setPassword(passwordEncoder.encode(client.getPassword()));
        clientRepository.save(client);
        log.info("'Cliente criado.'");

        try {
            emailService.enviarToken(client.getEmail(), "123456");

        } catch (Exception ex) {
            log.error("Erro ao enviar e-mail com token de ativação de conta para: {}", client.getEmail());
            throw new RuntimeException(ex);
        }

        log.info("'E-mail com token para ativação de conta enviado com sucesso.'");

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

        } catch (Exception ex) {
            log.error("'Não foi possível atualizar o nome ou e-mail do cliente: {}'", ex.getMessage());
            throw ex;
        }

        log.info("'Cliente atualizado com sucesso.'");
    }

}
