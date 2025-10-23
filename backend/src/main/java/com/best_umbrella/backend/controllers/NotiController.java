package com.best_umbrella.backend.controllers;

import com.best_umbrella.backend.model.Noti;
import com.best_umbrella.backend.repository.NotiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notificacoes")
@CrossOrigin(origins = "*")
public class NotiController {

    @Autowired
    private NotiRepository repository;

    @GetMapping
    public List<Noti> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Noti getById(@PathVariable Long id) {
        return repository.findById(id).orElse(null);
    }

    @PostMapping
    public Noti create(@RequestBody Noti noti) {
        return repository.save(noti);
    }

    @PutMapping("/{id}")
    public Noti markAsRead(@PathVariable Long id) {
        Noti noti = repository.findById(id).orElse(null);
        if (noti == null) return null;

        noti.setLida(true);
        return repository.save(noti);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
