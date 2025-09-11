package com.octalsystems.votehub.v1.repository;

import com.octalsystems.votehub.v1.entity.Scheme;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchemeRepository extends JpaRepository<Scheme, Long> {
}
