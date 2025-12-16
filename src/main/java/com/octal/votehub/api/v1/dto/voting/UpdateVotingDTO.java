package com.octal.votehub.api.v1.dto.voting;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.octal.votehub.api.v1.dto.candidate.CreateCandidatesDTO;
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
public class UpdateVotingDTO {
    private String title;

    private String description;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime expirationDate;

    private boolean generateQrcode;

    private boolean justify;

    private List<CreateCandidatesDTO> candidates;

}
