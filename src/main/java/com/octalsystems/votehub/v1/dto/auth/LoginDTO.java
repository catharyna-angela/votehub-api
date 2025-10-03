package com.octalsystems.votehub.v1.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LoginDTO {
    @NotBlank
    @Email(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$", message = "Insira um e-mail válido.")
    private String email;

    @NotBlank
    @Size(max = 6, min = 6, message = "A senha deve conter 6 caracteres.")
    private String password;
}
