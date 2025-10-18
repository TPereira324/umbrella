package com.best_umbrella.backend.service;

import com.best_umbrella.backend.model.GuardaChuva;
import com.best_umbrella.backend.repository.GuardaChuvaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GuardaChuvaService {
    private final GuardaChuvaRepository guardaChuvaRepository;

    @Autowired
    public GuardaChuvaService(GuardaChuvaRepository guardaChuvaRepository) {
        this.guardaChuvaRepository = guardaChuvaRepository;
    }

    public List<GuardaChuva> findAll() {
        return guardaChuvaRepository.findAll();
    }

    public Optional<GuardaChuva> findById(Long id) {
        return guardaChuvaRepository.findById(id);
    }

    public GuardaChuva findByCodigoQr(String codigoQr) {
        return guardaChuvaRepository.findByCodigoQr(codigoQr);
    }

    public GuardaChuva save(GuardaChuva guardaChuva) {
        return guardaChuvaRepository.save(guardaChuva);
    }

    public void deleteById(Long id) {
        guardaChuvaRepository.deleteById(id);
    }
}