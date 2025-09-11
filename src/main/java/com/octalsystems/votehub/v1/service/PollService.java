package com.octalsystems.votehub.v1.service;

import com.octalsystems.votehub.v1.entity.Poll;
import com.octalsystems.votehub.v1.repository.PollRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class PollService {

    private final PollRepository pollRepository;

    @Transactional
    public Poll save(Poll pollCreateDTO) {
        pollRepository.save(pollCreateDTO);
        log.info("'Enquete criada.'");

        return pollCreateDTO;
    }
}
