package com.best_umbrella.backend.controllers;

import com.best_umbrella.backend.model.Multa;
import com.best_umbrella.backend.service.MultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/multas")
public class MultaController {

    @Autowired
    private MultaService multaService;

    @PostMapping
    public ResponseEntity<Multa> aplicarMulta(@RequestBody Multa multa) {
        Multa novaMulta = multaService.aplicarMulta(multa.getMotivo(), multa.getDescricao());
        return ResponseEntity.ok(novaMulta);
    }
}