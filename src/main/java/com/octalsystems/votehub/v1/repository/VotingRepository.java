package com.octalsystems.votehub.v1.repository;

import com.octalsystems.votehub.v1.entity.Voting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VotingRepository extends JpaRepository<Voting, Long> {
}
