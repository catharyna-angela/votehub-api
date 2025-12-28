package com.octal.votehub.api.v1.service;

import com.octal.votehub.api.v1.entity.Candidate;
import com.octal.votehub.api.v1.entity.Client;
import com.octal.votehub.api.v1.entity.Voting;
import com.octal.votehub.api.v1.enums.SchemeType;
import com.octal.votehub.api.v1.repository.CandidateRepository;
import com.octal.votehub.api.v1.repository.ClientRepository;
import com.octal.votehub.api.v1.repository.VotingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
public class VotingService {

    private final VotingRepository votingRepository;
    private final ClientRepository clientRepository;
    private final CandidateRepository candidateRepository;

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
    public Voting update(Long votingId, Voting voting, Long clientId) {
        Voting existingVoting = votingRepository.findById(votingId)
                .orElseThrow(() -> new RuntimeException("Votação não existe ou o id está incorreto."));

        try {
            //validar se votação não obteve nenhum voto ainda, para então permitir a alteração.
            Long getClientId = existingVoting.getClient().getId();

            if (getClientId.equals(clientId)) {
                existingVoting.setTitle(voting.getTitle());
                existingVoting.setDescription(voting.getDescription());
                existingVoting.setExpirationDate(voting.getExpirationDate());
                existingVoting.setGenerateQrcode(voting.isGenerateQrcode());
                //atualizar candidates

                votingRepository.save(existingVoting);
                log.info("'Votação atualizada com sucesso.'");

            }

        } catch (Exception ex) {
            log.error("Não foi possível atualizar votação.");
            throw new RuntimeException("Erro ao fazer update da votação.");
        }

        return existingVoting;
    }

    @Transactional(readOnly = true)
    public Voting findVoting(Long id) {
        //adicionar coleta de dados do usuário.

        return votingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("'Votação não encontrada.'"));
    }

    @Transactional
    public Candidate vote(Long id, Candidate candidate) {
        Voting voting = votingRepository.findById(id).orElseThrow(() -> {
            log.error("'Votação não existe ou id incorreto.'");
            return new RuntimeException("Votação não existe no banco de dados.");
        });

        LocalDateTime dateNow = LocalDateTime.now();

        if (dateNow.isAfter(voting.getExpirationDate())){
            log.error("'Votação expirada, não é possível votar.'");
            throw new RuntimeException("'Votação expirada.'");
        }

        Long candidateId = candidate.getId();

        Candidate candidateExists = candidateRepository.findById(candidateId).orElseThrow(() -> {
            log.error("'Id do candidato não encontrado.'");
            return new RuntimeException("'Id do candidato não confere.'");
        });

        if (!candidateExists.getVoting().getId().equals(voting.getId())) {
            log.error("'Usuário está tentando votar em um candidato que não pertence à votação ou o id passado está incorreto.'");
            throw new RuntimeException("'O candidato a ser votado não pertence à votação ou id do candidato está incorreto.'");
        }

        candidateExists.setTotalVotes(candidateExists.getTotalVotes() + 1);

        return candidateExists;
    }

}
