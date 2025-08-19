package com.octalsystems.votehub.v1.repository;

import com.octalsystems.votehub.v1.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {

}
