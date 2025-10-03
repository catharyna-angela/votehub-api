package com.octalsystems.votehub.v1.controller;

import com.octalsystems.votehub.v1.dto.auth.LoginDTO;
import com.octalsystems.votehub.v1.dto.auth.LoginResponseDTO;
import com.octalsystems.votehub.v1.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

//    @PostMapping("/activation")
//    ResponseEntity<Void> accountValidation(){
//
//    }

    @PostMapping("/login")
    ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid LoginDTO loginDTO){
        LoginResponseDTO loginResponseDTO = authService.authenticate(loginDTO);
        return ResponseEntity.status(HttpStatus.OK).body(loginResponseDTO);
    }
}
