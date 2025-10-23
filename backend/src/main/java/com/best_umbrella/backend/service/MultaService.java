package com.best_umbrella.backend.service;

import com.best_umbrella.backend.model.Multa;
import com.best_umbrella.backend.repository.MultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MultaService {

    @Autowired
    private MultaRepository multaRepository;

    public Multa aplicarMulta(String motivo, String descricao) {
        Multa multa = new Multa("Atraso", "Devolução fora do prazo");
        return multaRepository.save(multa);
    }
}