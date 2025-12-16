package com.octal.votehub.api.v1.controller;

import com.octal.votehub.api.v1.dto.mapper.VotingMapper;
import com.octal.votehub.api.v1.dto.voting.CreateVotingDTO;
import com.octal.votehub.api.v1.dto.voting.ResponseCreateVotingDTO;
import com.octal.votehub.api.v1.dto.voting.ResponseUpdateVotingDTO;
import com.octal.votehub.api.v1.dto.voting.UpdateVotingDTO;
import com.octal.votehub.api.v1.entity.Voting;
import com.octal.votehub.api.v1.jwt.UserDetailsImpl;
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
                                                          @RequestBody UpdateVotingDTO updateVotingDTO,
                                                          @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Voting voting = votingService.update(id ,VotingMapper.toVoting(updateVotingDTO), userDetails.getId());
        return ResponseEntity.status(HttpStatus.OK).body(VotingMapper.toResponseUpdateVotingDTO(voting));
    }

}
