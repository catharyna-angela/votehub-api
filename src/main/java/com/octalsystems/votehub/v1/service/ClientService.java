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
            log.error("'Usuário já existe com o mesmo e-mail.'");
            throw new IllegalArgumentException("'Não foi possível concluir o cadastro de conta.'");
        }

        client.setPassword(passwordEncoder.encode(client.getPassword()));
        clientRepository.save(client);
        log.info("'Cliente criado.'");

        try {
            emailService.sendCode(client.getEmail());

        } catch (Exception ex) {
            log.error("Erro ao enviar e-mail com token de ativação de conta para: {}", client.getEmail());
            throw new RuntimeException(ex);
        }

        log.info("'E-mail com token para ativação de conta enviado com sucesso.'");

        return client;
    }

    @Transactional
    public void update(Client client, String email) {
        try {
            Client existingClient = clientRepository.findByEmail(email)
                    .orElseThrow(() -> new RuntimeException("'Id do cliente está incorreto ou não existe.'"));

            if (client.getName() != null && !client.getName().isBlank()) {
                existingClient.setName(client.getName());
            }

            if (client.getEmail() != null && !client.getEmail().isBlank()) {
                existingClient.setEmail(client.getEmail());
            }

            clientRepository.save(existingClient);

        } catch (Exception ex) {
            log.error("'Não foi possível atualizar o nome ou e-mail do cliente.'");
            throw new RuntimeException("'Não foi possível atualizar dados do cliente.'");
        }

        log.info("'Cliente atualizado com sucesso.'");
    }

}
