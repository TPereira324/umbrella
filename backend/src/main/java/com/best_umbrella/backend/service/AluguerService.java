package com.best_umbrella.backend.service;

import com.best_umbrella.backend.model.Aluguer;
import com.best_umbrella.backend.repository.AluguerRepository;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AluguerService {

    private final AluguerRepository aluguerRepository;

    public AluguerService(AluguerRepository aluguerRepository) {
        this.aluguerRepository = aluguerRepository;
    }

    public List<Aluguer> getAll() {
        return aluguerRepository.findAll();
    }

    public Aluguer save(Aluguer aluguer) {
        // ⚙️ Lógica da multa de 100€ se o guarda-chuva não for devolvido após 24h
        if (aluguer.getDataDevolucao() != null && aluguer.getDataAluguer() != null) {
            long horas = Duration.between(aluguer.getDataAluguer(), aluguer.getDataDevolucao()).toHours();
            if (horas > 24) {
                aluguer.setMulta(100.0);
            }
        }
        return aluguerRepository.save(aluguer);
    }
}
