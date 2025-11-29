package com.octal.votehub.api.v1.dto.voting;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.octal.votehub.api.v1.dto.candidate.CreateCandidatesDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateVotingDTO {
    @NotBlank
    private String title;

    @NotBlank
    private String description;

    @NotNull(message = "A data e hora de expiração é obrigatória.")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime expirationDate;

    private boolean generateQrcode;

    private boolean justify;

    @NotNull(message = "É necessário adicionar os candidatos.")
    private List<CreateCandidatesDTO> candidates;

}
