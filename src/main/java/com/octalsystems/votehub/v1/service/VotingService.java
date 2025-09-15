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
    public Voting save(Voting createVotingDTO) {
        createVotingDTO.setSchemeType(SchemeType.VOTACAO);
        votingRepository.save(createVotingDTO);
        log.info("'Votação criada.'");

        return createVotingDTO;
    }

    @Transactional
    public void update(Long id, Voting updateVotingDTO) {
        try {
            Voting existingVoting = votingRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Id da votação está incorreto ou não existe."));

            existingVoting.setTitle(updateVotingDTO.getTitle());
            existingVoting.setDescription(updateVotingDTO.getDescription());
            existingVoting.setExpirationDate(updateVotingDTO.getExpirationDate());
            existingVoting.setGenerateQrcode(updateVotingDTO.isGenerateQrcode());

            votingRepository.save(existingVoting);
            log.info("'Votação atualizada com sucesso.'");

        } catch (Exception ex) {
            log.error("Erro ao atualizar votação: {}", ex.getMessage());
            throw ex;
        }

    }
}
