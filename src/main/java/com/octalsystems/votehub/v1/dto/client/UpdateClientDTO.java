package com.octalsystems.votehub.v1.dto.client;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateClientDTO {
    @Email(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$", message = "Insira um e-mail válido.")
    private String email;

    @Size(max = 46)
    private String name;

}
