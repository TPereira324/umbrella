package com.best_umbrella.backend.controllers;

import com.best_umbrella.backend.model.Utilizador;
import com.best_umbrella.backend.service.UtilizadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/Utilizador")
public class UtilizadorController {

    private final UtilizadorService utilizadorService;

    @Autowired
    public UtilizadorController(UtilizadorService utilizadorService) {
        this.utilizadorService = utilizadorService;
    }

    @GetMapping
    public ResponseEntity<List<Utilizador>> getAllUtilizadores() {
        return ResponseEntity.ok(utilizadorService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Utilizador> getUtilizadorById(@PathVariable Long id) {
        Optional<Utilizador> utilizador = utilizadorService.findById(Long.valueOf(id));
        return utilizador.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Utilizador> createUtilizador(@RequestBody Utilizador utilizador) {
        Utilizador savedUtilizador = utilizadorService.save(utilizador);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUtilizador);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Utilizador> updateUtilizador(@PathVariable Integer id, @RequestBody Utilizador utilizador) {
        if (!utilizadorService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        utilizador.setUtilizadorId(id);
        return ResponseEntity.ok(utilizadorService.save(utilizador));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUtilizador(@PathVariable Long id) {
        if (!utilizadorService.findById(Long.valueOf(id)).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        utilizadorService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}