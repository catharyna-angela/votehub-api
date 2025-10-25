package com.octalsystems.votehub.v1.dto.authentication;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponseDTO {
    private String token;
    private String tokenType;
    private long expiresIn;

}
