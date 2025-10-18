package com.best_umbrella.backend.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.best_umbrella.backend.model.GuardaChuva;

@Repository
public interface GuardaChuvaRepository extends JpaRepository<GuardaChuva, Long> {
    GuardaChuva findByCodigoQr(String codigoQr);
}