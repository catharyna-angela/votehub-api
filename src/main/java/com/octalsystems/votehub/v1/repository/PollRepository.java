package com.octalsystems.votehub.v1.repository;

import com.octalsystems.votehub.v1.entity.Poll;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PollRepository extends JpaRepository<Poll, Long> {
}
