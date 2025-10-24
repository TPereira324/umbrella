package com.best_umbrella.backend.repository;

import com.best_umbrella.backend.model.Utilizador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtilizadorRepository extends JpaRepository<Utilizador, Integer> {
    Utilizador findByEmail(String email);
}