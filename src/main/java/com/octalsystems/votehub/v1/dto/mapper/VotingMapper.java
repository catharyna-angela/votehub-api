package com.octalsystems.votehub.v1.dto.mapper;

import com.octalsystems.votehub.v1.dto.voting.CreateVotingDTO;
import com.octalsystems.votehub.v1.dto.voting.ResponseCreateVotingDTO;
import com.octalsystems.votehub.v1.entity.Voting;
import org.modelmapper.ModelMapper;

public class VotingMapper {

    public static Voting toVoting(CreateVotingDTO createVotingDTO) {
        return new ModelMapper().map(createVotingDTO, Voting.class);
    }

    public static ResponseCreateVotingDTO toResponseCreateVotingDTO(Voting voting) {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(voting, ResponseCreateVotingDTO.class);
    }
}
