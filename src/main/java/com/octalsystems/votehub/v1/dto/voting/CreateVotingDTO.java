package com.octalsystems.votehub.v1.dto.voting;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

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

}
