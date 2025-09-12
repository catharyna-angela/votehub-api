package com.octalsystems.votehub.v1.dto;

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
    private boolean validated;

}
