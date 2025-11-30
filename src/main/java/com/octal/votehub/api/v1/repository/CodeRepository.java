package com.octal.votehub.api.v1.repository;

import com.octal.votehub.api.v1.entity.Code;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CodeRepository extends JpaRepository<Code, Long> {

}
