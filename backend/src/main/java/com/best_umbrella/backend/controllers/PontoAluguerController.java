package com.best_umbrella.backend.controllers;

import com.best_umbrella.backend.model.PontodeAluguer;
import com.best_umbrella.backend.repository.PontodeAluguerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pontos")
@CrossOrigin(origins = "*") // permite chamadas do frontend
public class PontoAluguerController {

    @Autowired
    private PontodeAluguerRepository repository;

    @GetMapping
    public List<PontodeAluguer> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public PontodeAluguer getById(@PathVariable Long id) {
        return repository.findById(id).orElse(null);
    }

    @PostMapping
    public PontodeAluguer create(@RequestBody PontodeAluguer ponto) {
        return repository.save(ponto);
    }

    @PutMapping("/{id}")
    public PontodeAluguer update(@PathVariable Long id, @RequestBody PontodeAluguer updated) {
        PontodeAluguer existing = repository.findById(id).orElse(null);
        if (existing == null) return null;

        existing.setNome(updated.getNome());
        existing.setLatitude(updated.getLatitude());
        existing.setLongitude(updated.getLongitude());
        existing.setTotalGuardaChuvas(updated.getTotalGuardaChuvas());
        return repository.save(existing);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
