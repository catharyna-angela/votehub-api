package com.octalsystems.votehub.v1.dto.voting;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

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

    @NotBlank
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") //fixme: mudar para dia, mes, ano
    private LocalDateTime expirationDate;

    private boolean generateQrcode;

    private boolean justify;

}
