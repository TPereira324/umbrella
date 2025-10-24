package com.best_umbrella.backend.service;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.best_umbrella.backend.model.Utilizador;
import com.best_umbrella.backend.repository.UtilizadorRepository;

@Service
public class UtilizadorService {

    private final UtilizadorRepository utilizadorRepository;

    @Autowired
    public UtilizadorService(UtilizadorRepository utilizadorRepository) {
        this.utilizadorRepository = utilizadorRepository;
    }

    public List<Utilizador> findAll() {
        return utilizadorRepository.findAll();
    }

    public Optional<Utilizador> findById(Long id) {
        return utilizadorRepository.findById(id);
    }

    public Utilizador findByEmail(String email) {
        return utilizadorRepository.findByEmail(email);
    }

    public Utilizador save(Utilizador utilizador) {
        if (utilizador.getDataRegisto() == null) {
            utilizador.setDataRegisto(LocalDateTime.now());
        }
        return utilizadorRepository.save(utilizador);
    }

    public void deleteById(Long id) {
        utilizadorRepository.deleteById(id);
    }
}