package com.octal.votehub.api.v1.dto.voting;

import com.octal.votehub.api.v1.dto.candidate.ResponseCandidateDTO;
import com.octal.votehub.api.v1.domain.enums.SchemeType;
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
public class ResponseUpdateVotingDTO {
    private Long id;
    private String title;
    private String description;
    private String expirationDate;
    private String url;
    private boolean generateQrcode;
    private boolean justify;
    private SchemeType schemeType;
    private LocalDateTime createdDate;
    private List<ResponseCandidateDTO> candidates;

}
