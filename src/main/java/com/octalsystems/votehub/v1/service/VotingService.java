package com.octalsystems.votehub.v1.service;

import com.octalsystems.votehub.v1.entity.Voting;
import com.octalsystems.votehub.v1.repository.VotingRepository;
import com.octalsystems.votehub.v1.utils.SchemeType;
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
    public Voting save(Voting voting) {
        voting.setSchemeType(SchemeType.VOTACAO);
        votingRepository.save(voting);
        log.info("'Votação criada.'");

        return voting;
    }

    @Transactional
    public void update(Long id, Voting voting) {
        try {
            Voting existingVoting = votingRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Id da votação está incorreto ou não existe."));

            existingVoting.setTitle(voting.getTitle());
            existingVoting.setDescription(voting.getDescription());
            existingVoting.setExpirationDate(voting.getExpirationDate());
            existingVoting.setGenerateQrcode(voting.isGenerateQrcode());

            votingRepository.save(existingVoting);
            log.info("'Votação atualizada com sucesso.'");

        } catch (Exception ex) {
            log.error("Erro ao atualizar votação: {}", ex.getMessage());
            throw ex;
        }

    }
}
