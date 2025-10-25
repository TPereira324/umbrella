package com.best_umbrella.backend.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.best_umbrella.backend.model.Multa;
import com.best_umbrella.backend.service.MultaService;

@RestController
@RequestMapping("/api/Multa")
public class MultaController {

    private final MultaService multaService;

    @Autowired
    public MultaController(MultaService multaService) {
        this.multaService = multaService;
    }

    @GetMapping
    public ResponseEntity<List<Multa>> getAllMultas() {
        return ResponseEntity.ok(multaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Multa> getMultaById(@PathVariable Long id) {
        Optional<Multa> multa = multaService.findById(id);
        return multa.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/utilizador/{utilizadorId}")
    public ResponseEntity<List<Multa>> getMultasByUtilizador(@PathVariable Long utilizadorId) {
        return ResponseEntity.ok(multaService.findByUtilizadorId(utilizadorId));
    }

    @GetMapping("/aluguer/{aluguerId}")
    public ResponseEntity<List<Multa>> getMultasByAluguer(@PathVariable Long aluguerId) {
        return ResponseEntity.ok(multaService.findByAluguerId(aluguerId));
    }

    @PostMapping
    public ResponseEntity<Multa> createMulta(@RequestBody Multa multa) {
        Multa saved = multaService.save(multa);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    // Criação orientada a Aluguer (sem enviar entidades completas)
    @PostMapping("/atrelada")
    public ResponseEntity<Multa> createMultaAtrelada(
            @RequestParam Long aluguerId,
            @RequestParam Double valor,
            @RequestParam String motivo,
            @RequestParam(required = false) String descricao,
            @RequestParam(required = false) String moeda
    ) {
        Multa saved = multaService.criarMultaParaAluguer(aluguerId, valor, motivo, descricao, moeda);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PostMapping("/{id}/pagar")
    public ResponseEntity<Multa> pagarMulta(@PathVariable Long id) {
        Optional<Multa> multa = multaService.pagarMulta(id);
        return multa.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/{id}/cancelar")
    public ResponseEntity<Multa> cancelarMulta(@PathVariable Long id) {
        Optional<Multa> multa = multaService.cancelarMulta(id);
        return multa.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}