package com.octalsystems.votehub.v1.dto.candidate;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateCandidatesDTO {
    @NotBlank
    private String name;
}
