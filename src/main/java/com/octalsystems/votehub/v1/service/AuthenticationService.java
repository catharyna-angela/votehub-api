package com.octalsystems.votehub.v1.service;

import com.octalsystems.votehub.v1.dto.authentication.AccountActivationDTO;
import com.octalsystems.votehub.v1.dto.authentication.LoginDTO;
import com.octalsystems.votehub.v1.dto.authentication.LoginResponseDTO;
import com.octalsystems.votehub.v1.entity.Client;
import com.octalsystems.votehub.v1.jwt.JwtService;
import com.octalsystems.votehub.v1.jwt.UserDetailsImpl;
import com.octalsystems.votehub.v1.repository.ClientRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthenticationService {

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final ClientRepository clientRepository;

    public LoginResponseDTO authenticate(LoginDTO loginDTO) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginDTO.getEmail(), //principal
                            loginDTO.getPassword() //credentials
                    )
            );

            log.info("'Usuário autenticado com sucesso.'");

            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

            return jwtService.generateToken(
                    userDetails.getRole(),
                    String.valueOf(userDetails.getId()),
                    userDetails.getUsername(),
                    "votehub-api"
            );

        } catch (Exception ex) {
            throw new IllegalArgumentException("'Credenciais inválidas'", ex);
        }
    }

    @Transactional
    public void activation(AccountActivationDTO accountActivationDTO) {
        Client client = clientRepository.findByEmail(accountActivationDTO.getEmail())
                .orElseThrow(() -> {
                    log.error("'E-mail de usuário inserido incorretamente ou não existe.'");
                    return new RuntimeException("'Não foi possível ativar conta, tente novamente.'");
                });

        if (!accountActivationDTO.getCode().equals("123456")) {
            log.error("'Código de ativação de conta não confere.'");
            throw new RuntimeException("'Não foi possível ativar conta, tente novamente.'");
        }

        client.setActivated(true);
        log.info("'Conta ativada com sucesso.'");

    }

//    public void resend(String emailDTO) {
//        Optional<Client> accountExists = clientRepository.findByEmail(emailDTO);
//
//        if (accountExists.isPresent()) {
//
//        }

}
