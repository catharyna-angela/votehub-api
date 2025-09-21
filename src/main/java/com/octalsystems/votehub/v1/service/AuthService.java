package com.octalsystems.votehub.v1.service;

import com.octalsystems.votehub.v1.dto.LoginDTO;
import com.octalsystems.votehub.v1.dto.LoginResponseDTO;
import com.octalsystems.votehub.v1.jwt.JwtService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthService {

    private final JwtService jwtService;

    public LoginResponseDTO authenticate(@Valid LoginDTO loginDTO) {

        return null;
    }
}
