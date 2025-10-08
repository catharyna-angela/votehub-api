package com.octalsystems.votehub.v1.service;

import com.octalsystems.votehub.v1.dto.auth.LoginDTO;
import com.octalsystems.votehub.v1.dto.auth.LoginResponseDTO;
import com.octalsystems.votehub.v1.entity.Client;
import com.octalsystems.votehub.v1.jwt.JwtService;
import com.octalsystems.votehub.v1.jwt.UserDetailsImpl;
import com.octalsystems.votehub.v1.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthService {

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final Client client;
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

    public void activation(String code) { //valida código recebido do cliente para ativar a conta
        if (!code.equals("123456")) { //se o código recebido não for igual, lança um erro
            throw new IllegalArgumentException("'Código fornecido não confere.'");
        }
        client.setActive(true);//consultar o cliente no banco e setar.
        log.info("'Validação de conta realizada com sucesso.'");

    }

//    public void resend(String emailDTO) {
//        Optional<Client> accountExists = clientRepository.findByEmail(emailDTO);
//
//        if (accountExists.isPresent()) {
//
//        }
//    }
}
