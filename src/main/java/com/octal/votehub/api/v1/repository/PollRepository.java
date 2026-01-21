package com.octal.votehub.api.v1.repository;

import com.octal.votehub.api.v1.domain.entity.Poll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PollRepository extends JpaRepository<Poll, Long> {
}
