package com.octal.votehub.api.v1.service;

import com.octal.votehub.api.v1.dto.authentication.AccountActivationDTO;
import com.octal.votehub.api.v1.dto.authentication.LoginDTO;
import com.octal.votehub.api.v1.dto.authentication.LoginResponseDTO;
import com.octal.votehub.api.v1.dto.authentication.ResendEmailDTO;
import com.octal.votehub.api.v1.entity.Client;
import com.octal.votehub.api.v1.jwt.JwtService;
import com.octal.votehub.api.v1.jwt.UserDetailsImpl;
import com.octal.votehub.api.v1.repository.ClientRepository;
import com.octal.votehub.api.v1.util.CodeService;
import com.octal.votehub.api.v1.util.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthenticationService {

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final ClientRepository clientRepository;
    private final EmailService emailService;
    private final CodeService codeService;

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

            if (!userDetails.isAccountActivated()) {
                log.error("'Conta de usuário ainda não foi ativada.'");
                throw new RuntimeException("'Não foi possível fazer o login.'");
            }

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

        if (client.isActivated()) {
            log.error("'A conta do cliente já está ativa.'");
            throw new RuntimeException("'Não foi possível ativar a conta.'");
        }

        codeService.validate(accountActivationDTO.getCode());

        client.setActivated(true);
        log.info("'Conta ativada com sucesso.'");
    }

    @Transactional
    public void resend(ResendEmailDTO resendEmailDTO) {
        Client client = clientRepository.findByEmail(resendEmailDTO.getEmail())
                .orElseThrow(() -> {
                    log.error("'E-mail não existe ou foi inserido incorretamente.'");
                    return new RuntimeException("'Não foi possível reenviar o e-mail para ativação de conta, tente novamente.'");
                });

        if (client.isActivated()) {
            log.error("'Cliente já tem a conta ativada.'");
            throw new RuntimeException("'Não foi possível reenviar o e-mail para ativação de conta.'");
        }

        String code = codeService.generateAndSave();
        emailService.sendCode(resendEmailDTO.getEmail(), code);
        log.info("'E-mail com novo token para ativação de conta reenviado com sucesso.'");
    }

}
