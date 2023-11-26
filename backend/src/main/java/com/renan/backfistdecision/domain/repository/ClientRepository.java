package com.renan.backfistdecision.domain.repository;

import com.renan.backfistdecision.domain.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositório da entidade Client
 * @author Renan Peres
 */
public interface ClientRepository extends JpaRepository<Client, Long> {
}
