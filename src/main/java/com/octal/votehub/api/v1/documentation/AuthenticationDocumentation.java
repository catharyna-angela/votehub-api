package com.octal.votehub.api.v1.documentation;

import com.octal.votehub.api.v1.dto.authentication.AccountActivationDTO;
import com.octal.votehub.api.v1.dto.authentication.LoginDTO;
import com.octal.votehub.api.v1.dto.authentication.LoginResponseDTO;
import com.octal.votehub.api.v1.dto.authentication.ResendEmailDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Autenticação", description = "Recurso destinado à autenticação, autorização e ativação de conta.")
public interface AuthenticationDocumentation {

    @Operation(description = "Recurso para ativação conta.",
            responses = {
                    @ApiResponse(responseCode = "404", description = "E-mail de usuário inserido incorretamente ou não existe."),
                    @ApiResponse(responseCode = "409", description = "A conta do cliente já está ativa."),
                    @ApiResponse(responseCode = "200", description = "Conta ativada com sucesso.")
            })
    @PostMapping("/activation")
    ResponseEntity<Void> activation(@RequestBody @Valid AccountActivationDTO accountActivationDTO);

    @Operation(description = "Recurso para reenvio de código de ativação de conta.",
            responses = {
                    @ApiResponse(responseCode = "404", description = "E-mail não existe ou foi inserido incorretamente."),
                    @ApiResponse(responseCode = "409", description = "O usuário já tem a conta ativada."),
                    @ApiResponse(responseCode = "200", description = "E-mail com novo token para ativação de conta reenviado com sucesso.")
            })
    @PostMapping("/resend-activation")
    ResponseEntity<Void> resend(@RequestBody @Valid ResendEmailDTO resendEmailDTO);

    @Operation(description = "Recurso para login de usuário.",
            responses = {
                    @ApiResponse(responseCode = "404", description = "E-mail de usuário inserido incorretamente ou não existe."),
                    @ApiResponse(responseCode = "403", description = "Conta de usuário ainda não foi ativada."),
                    @ApiResponse(responseCode = "409", description = "A conta do cliente já está ativa."),
                    @ApiResponse(responseCode = "200", description = "Usuário autenticado com sucesso."),
            })
    @PostMapping("/login")
    ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid LoginDTO loginDTO);
}
