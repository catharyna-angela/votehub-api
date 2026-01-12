package com.octal.votehub.api.v1.dto.mapper;

import com.octal.votehub.api.v1.dto.candidate.ChooseCandidateDTO;
import com.octal.votehub.api.v1.dto.candidate.ResponseCandidateDTO;
import com.octal.votehub.api.v1.domain.entity.Candidate;
import org.modelmapper.ModelMapper;

public class CandidateMapper {

    public static Candidate toCandidate(ChooseCandidateDTO chooseCandidateDTO) {
        return new ModelMapper().map(chooseCandidateDTO, Candidate.class);
    }

    public static ResponseCandidateDTO toResponseCandidateDTO(Candidate candidate){
        return new ModelMapper().map(candidate, ResponseCandidateDTO.class);
    }

}
