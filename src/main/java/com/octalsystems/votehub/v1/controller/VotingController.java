package com.octalsystems.votehub.v1.controller;

import com.octalsystems.votehub.v1.entity.Voting;
import com.octalsystems.votehub.v1.service.VotingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/voting")
public class VotingController {

    private final VotingService votingService;

    @PostMapping
    public ResponseEntity<Voting> create(@RequestBody Voting votingCreateDTO){
        Voting voting = votingService.save(votingCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(voting);
    }
}
