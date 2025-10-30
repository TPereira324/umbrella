package com.best_umbrella.backend.repository;

import com.best_umbrella.backend.model.PontodeAluguer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PontodeAluguerRepository extends JpaRepository<PontodeAluguer, Long> {
}
