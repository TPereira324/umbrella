package com.best_umbrella.backend.controllers;

import com.best_umbrella.backend.model.Aluguer;
import com.best_umbrella.backend.service.AluguerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alugueres")
@CrossOrigin(origins = "*")
public class AluguerController {

    private final AluguerService aluguerService;

    public AluguerController(AluguerService aluguerService) {
        this.aluguerService = aluguerService;
    }

    @GetMapping
    public List<Aluguer> getAll() {
        return aluguerService.getAll();
    }

    @PostMapping
    public Aluguer criarAluguer(@RequestBody Aluguer aluguer) {
        return aluguerService.save(aluguer);
    }
}
