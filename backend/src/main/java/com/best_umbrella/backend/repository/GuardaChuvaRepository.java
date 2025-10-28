package com.best_umbrella.backend.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.best_umbrella.backend.model.GuardaChuva;


public interface GuardaChuvaRepository extends JpaRepository<GuardaChuva, Long> {
    GuardaChuva findByCodigoQr(String codigoQr);


}
