package com.octalsystems.votehub.v1.service;

import com.octalsystems.votehub.v1.dto.auth.LoginDTO;
import com.octalsystems.votehub.v1.dto.auth.LoginResponseDTO;
import com.octalsystems.votehub.v1.jwt.JwtService;
import com.octalsystems.votehub.v1.jwt.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthService {

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

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
            throw new RuntimeException("'Credenciais inválidas'", ex);
        }
    }
}
