package com.octal.votehub.api.v1.repository;

import com.octal.votehub.api.v1.entity.Code;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CodeRepository extends JpaRepository<Code, Long> {

    @Query("SELECT c FROM Code c WHERE c.activationCode = :activationCode")
    Optional<Code> findByCode(String activationCode);

}
