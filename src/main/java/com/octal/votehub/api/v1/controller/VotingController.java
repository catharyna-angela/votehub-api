package com.octal.votehub.api.v1.controller;

import com.octal.votehub.api.v1.dto.candidate.ChooseCandidateDTO;
import com.octal.votehub.api.v1.dto.candidate.ResponseCandidateDTO;
import com.octal.votehub.api.v1.mapper.CandidateMapper;
import com.octal.votehub.api.v1.mapper.VotingMapper;
import com.octal.votehub.api.v1.dto.voting.*;
import com.octal.votehub.api.v1.domain.entity.Candidate;
import com.octal.votehub.api.v1.domain.entity.Voting;
import com.octal.votehub.api.v1.security.jwt.UserDetailsImpl;
import com.octal.votehub.api.v1.service.VotingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/schemes/voting")
public class VotingController {

    private final VotingService votingService;

    @PostMapping
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<ResponseCreateVotingDTO> create(@Valid @RequestBody CreateVotingDTO createVotingDTO,
                                                          @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Voting voting = votingService.save(VotingMapper.toVoting(createVotingDTO), userDetails.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(VotingMapper.toResponseCreateVotingDTO(voting));
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<ResponseUpdateVotingDTO> update(@PathVariable Long id,
                                                          @Valid @RequestBody UpdateVotingDTO updateVotingDTO,
                                                          @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Voting voting = votingService.update(id ,VotingMapper.toVoting(updateVotingDTO), userDetails.getId());
        return ResponseEntity.status(HttpStatus.OK).body(VotingMapper.toResponseUpdateVotingDTO(voting));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseVotingDTO> get(@PathVariable Long id){ //fixme: gerar e passar token como parâmetro usando squids-java.
        Voting voting = votingService.findVoting(id) ;
        return ResponseEntity.status(HttpStatus.OK).body(VotingMapper.toResponseVotingDTO(voting));
    }

    @PostMapping("/{id}/vote")
    public ResponseEntity<ResponseCandidateDTO> vote(@PathVariable Long id, //passar token no path.
                                                     @Valid @RequestBody ChooseCandidateDTO chooseCandidateDTO){
        Candidate candidate = votingService.vote(id, CandidateMapper.toCandidate(chooseCandidateDTO));
        return ResponseEntity.status(HttpStatus.OK).body(CandidateMapper.toResponseCandidateDTO(candidate));
    }

}
