package com.octal.votehub.api.v1.dto.candidate;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ChooseCandidateDTO {
    @NotNull
    private Long id;

}
