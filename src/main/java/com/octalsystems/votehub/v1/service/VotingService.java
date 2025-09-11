package com.octalsystems.votehub.v1.service;

import com.octalsystems.votehub.v1.entity.Poll;
import com.octalsystems.votehub.v1.entity.Voting;
import com.octalsystems.votehub.v1.repository.PollRepository;
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

    @Transactional
    public void update(Long pollId, Voting votingUpdateDTO) {
        try {
            Voting existingVoting = votingRepository.findById(pollId)
                    .orElseThrow(() -> new RuntimeException("Id da votação está incorreto ou não existe."));

            existingVoting.setTitle(votingUpdateDTO.getTitle());
            existingVoting.setDescription(votingUpdateDTO.getDescription());
            existingVoting.setExpirationDate(votingUpdateDTO.getExpirationDate());
            existingVoting.setGenerateQrcode(votingUpdateDTO.isGenerateQrcode());

            votingRepository.save(existingVoting);
            log.info("'Votação atualizada com sucesso.'");

        } catch (Exception ex) {
            log.error("Erro ao atualizar votação: {}", ex.getMessage());
            throw ex;
        }

    }
}
