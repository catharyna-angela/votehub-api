package com.octalsystems.votehub.v1.dto.voting;

import com.octalsystems.votehub.v1.dto.candidate.ResponseCandidatesDTO;
import com.octalsystems.votehub.v1.enums.SchemeType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseCreateVotingDTO {
    private Long id;
    private String title;
    private String description;
    private String expirationDate;
    private String url;
    private boolean generateQrcode;
    private boolean justify;
    private SchemeType schemeType;
    private LocalDateTime createdDate;
    private List<ResponseCandidatesDTO> candidates;
}
