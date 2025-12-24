package com.octal.votehub.api.v1.dto.voting;

import com.octal.votehub.api.v1.dto.candidate.ResponseVotingCandidatesDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseVotingDTO {
    private String title;
    private String description;
    private String expirationDate;
    private List<ResponseVotingCandidatesDTO> candidates;
}
