package com.octalsystems.votehub.v1.service;

import com.octalsystems.votehub.v1.entity.Voting;
import com.octalsystems.votehub.v1.repository.VotingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class VotingService {

    private final VotingRepository votingRepository;

    @Transactional
    public Voting save(Voting votingCreateDTO) {
        votingRepository.save(votingCreateDTO);
        log.info("'Votação criada.'");

        return votingCreateDTO;
    }
}
