package com.octal.votehub.api.v1.dto.client;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ResponseClientDTO {
    private Long id;
    private String name;
    private String email;
    private String role;
}
