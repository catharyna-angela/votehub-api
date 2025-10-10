package com.octalsystems.votehub.v1.dto.auth;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LoginResponseDTO {
    private String token;
    private String tokenType;
    private long expiresIn;

}
