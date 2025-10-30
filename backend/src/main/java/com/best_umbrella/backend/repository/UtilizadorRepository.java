package com.best_umbrella.backend.repository;

import com.best_umbrella.backend.model.Utilizador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtilizadorRepository extends JpaRepository<Utilizador, Long> {
        Utilizador findByEmail(String email);
}
