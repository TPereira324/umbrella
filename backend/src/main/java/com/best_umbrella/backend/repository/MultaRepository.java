package com.best_umbrella.backend.repository;

import com.best_umbrella.backend.model.Multa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MultaRepository extends JpaRepository<Multa, Long> {
    // Aqui podes adicionar métodos personalizados, se necessário
}