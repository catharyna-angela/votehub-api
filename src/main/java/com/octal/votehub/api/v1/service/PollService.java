package com.octal.votehub.api.v1.service;

import com.octal.votehub.api.v1.domain.entity.Poll;
import com.octal.votehub.api.v1.domain.enums.SchemeType;
import com.octal.votehub.api.v1.repository.PollRepository;
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
