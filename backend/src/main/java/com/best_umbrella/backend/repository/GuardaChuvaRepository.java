package com.best_umbrella.backend.repository;

import com.best_umbrella.backend.model.GuardaChuva;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuardaChuvaRepository extends JpaRepository<GuardaChuva, Long> {
    GuardaChuva findByCodigoQr(String codigoQr);
}
