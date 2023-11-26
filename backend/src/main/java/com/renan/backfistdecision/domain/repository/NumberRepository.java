package com.renan.backfistdecision.domain.repository;

import com.renan.backfistdecision.domain.entity.Number;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositório da entidade Number
 * @author Renan Peres
 */
public interface NumberRepository extends JpaRepository<Number, Long> {
}
