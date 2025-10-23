package com.best_umbrella.backend.repository;

import com.best_umbrella.backend.model.Aluguer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AluguerRepository extends JpaRepository<Aluguer, Long> {
}
