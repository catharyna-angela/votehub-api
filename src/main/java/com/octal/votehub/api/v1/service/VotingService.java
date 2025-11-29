package com.octal.votehub.api.v1.service;

import com.octal.votehub.api.v1.entity.Candidate;
import com.octal.votehub.api.v1.entity.Client;
import com.octal.votehub.api.v1.entity.Voting;
import com.octal.votehub.api.v1.enums.SchemeType;
import com.octal.votehub.api.v1.repository.ClientRepository;
import com.octal.votehub.api.v1.repository.VotingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class VotingService {

    private final VotingRepository votingRepository;
    private final ClientRepository clientRepository;

    @Transactional
    public Voting save(Voting voting, Long clientId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("'Cliente não encontrado.'"));

        voting.setSchemeType(SchemeType.VOTACAO);
        voting.setClient(client);

        if (voting.getCandidates() != null) {
            for (Candidate candidate : voting.getCandidates()) {
                candidate.setVoting(voting);
            }
        }

        votingRepository.save(voting);
        log.info("'Votação criada.'");

        return voting;
    }

    @Transactional
    public void update(Long id, Voting updateVotingDTO, Long clientId) { //permitir atualizar votação se não houver nenhum voto.
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
