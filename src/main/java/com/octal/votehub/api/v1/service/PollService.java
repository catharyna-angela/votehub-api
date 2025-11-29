package com.octal.votehub.api.v1.service;

import com.octal.votehub.api.v1.entity.Poll;
import com.octal.votehub.api.v1.repository.PollRepository;
import com.octal.votehub.api.v1.enums.SchemeType;
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
    public Poll save(Poll createPollDTO) {
        createPollDTO.setSchemeType(SchemeType.ENQUETE);
        pollRepository.save(createPollDTO);
        log.info("'Enquete criada.'");

        return createPollDTO;
    }
}
