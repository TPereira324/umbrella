package com.best_umbrella.backend.repository;

import com.best_umbrella.backend.model.Noti;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotiRepository extends JpaRepository<Noti, Long> {

    // 🔹 Lista todas as notificações de um utilizador específico
    List<Noti> findByUtilizadorIdOrderByDataEnvioDesc(Long utilizadorId);
}
