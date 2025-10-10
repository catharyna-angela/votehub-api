package com.octalsystems.votehub.v1.controller;

import com.octalsystems.votehub.v1.dto.authentication.AccountActivationDTO;
import com.octalsystems.votehub.v1.dto.authentication.LoginDTO;
import com.octalsystems.votehub.v1.dto.authentication.LoginResponseDTO;
import com.octalsystems.votehub.v1.service.AuthenticationService;
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
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/activation")
    ResponseEntity<Void> activation(@RequestBody AccountActivationDTO accountActivationDTO){
        authenticationService.activation(accountActivationDTO);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

//    @PostMapping("/resend-activation")
//    ResponseEntity<Void> resend(@RequestBody String emailDTO){
//        authService.resend(emailDTO);
//        return ResponseEntity.status(HttpStatus.OK).build();
//    }

    @PostMapping("/login")
    ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid LoginDTO loginDTO){
        LoginResponseDTO loginResponseDTO = authenticationService.authenticate(loginDTO);
        return ResponseEntity.status(HttpStatus.OK).body(loginResponseDTO);
    }
}
