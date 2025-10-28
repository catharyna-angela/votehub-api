package com.octalsystems.votehub.v1.controller;

import com.octalsystems.votehub.v1.dto.mapper.VotingMapper;
import com.octalsystems.votehub.v1.dto.voting.ResponseCreateVotingDTO;
import com.octalsystems.votehub.v1.dto.voting.CreateVotingDTO;
import com.octalsystems.votehub.v1.entity.Voting;
import com.octalsystems.votehub.v1.jwt.UserDetailsImpl;
import com.octalsystems.votehub.v1.service.VotingService;
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
                                                          @AuthenticationPrincipal UserDetailsImpl userDetails){
        Voting voting = votingService.save(VotingMapper.toVoting(createVotingDTO), userDetails.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(VotingMapper.toResponseCreateVotingDTO(voting));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('CLIENT') AND #id == authentication.principal.id")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody Voting votingUpdateDTO){ //se a votação não tiver nenhum voto ainda, permitir a alteração.
        votingService.update(id, votingUpdateDTO);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
