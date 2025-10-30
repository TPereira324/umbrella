package com.best_umbrella.backend.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.best_umbrella.backend.model.GuardaChuva;
import com.best_umbrella.backend.service.GuardaChuvaService;

@RestController
@RequestMapping("/api/guardachuvas")
public class GuardaChuvaController {

    private final GuardaChuvaService guardaChuvaService;

    @Autowired
    public GuardaChuvaController(GuardaChuvaService guardaChuvaService) {
        this.guardaChuvaService = guardaChuvaService;
    }

    @GetMapping
    public ResponseEntity<List<GuardaChuva>> getAllGuardaChuvas() {
        return ResponseEntity.ok(guardaChuvaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GuardaChuva> getGuardaChuvaById(@PathVariable Integer id) {
        Optional<GuardaChuva> guardaChuva = guardaChuvaService.findById(id);
        return guardaChuva.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/codigo/{codigoQr}")
    public ResponseEntity<GuardaChuva> getGuardaChuvaByCodigoQr(@PathVariable String codigoQr) {
        GuardaChuva guardaChuva = guardaChuvaService.findByCodigoQr(codigoQr);
        if (guardaChuva != null) {
            return ResponseEntity.ok(guardaChuva);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<GuardaChuva> createGuardaChuva(@RequestBody GuardaChuva guardaChuva) {
        GuardaChuva savedGuardaChuva = guardaChuvaService.save(guardaChuva);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedGuardaChuva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GuardaChuva> updateGuardaChuva(@PathVariable Integer id, @RequestBody GuardaChuva guardaChuva) {
        if (!guardaChuvaService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        guardaChuva.setGuardaChuvaId(id);
        return ResponseEntity.ok(guardaChuvaService.save(guardaChuva));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGuardaChuva(@PathVariable Integer id) {
        Optional<GuardaChuva> guardaChuva = guardaChuvaService.findById(id);
        if (guardaChuva.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        guardaChuvaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}