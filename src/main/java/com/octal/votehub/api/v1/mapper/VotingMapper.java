package com.octal.votehub.api.v1.mapper;

import com.octal.votehub.api.v1.dto.voting.*;
import com.octal.votehub.api.v1.domain.entity.Voting;
import org.modelmapper.ModelMapper;

public class VotingMapper {

    public static Voting toVoting(CreateVotingDTO createVotingDTO) {
        return new ModelMapper().map(createVotingDTO, Voting.class);
    }

    public static Voting toVoting(UpdateVotingDTO updateVotingDTO) {
        return new ModelMapper().map(updateVotingDTO, Voting.class);
    }

    public static ResponseCreateVotingDTO toResponseCreateVotingDTO(Voting voting) {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(voting, ResponseCreateVotingDTO.class);
    }

    public static ResponseUpdateVotingDTO toResponseUpdateVotingDTO(Voting voting) {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(voting, ResponseUpdateVotingDTO.class);
    }

    public static ResponseVotingDTO toResponseVotingDTO(Voting voting) {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(voting, ResponseVotingDTO.class);
    }
}
