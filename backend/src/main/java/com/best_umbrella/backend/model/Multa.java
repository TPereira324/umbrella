package com.best_umbrella.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

/**
 * Entidade que representa uma multa aplicada ao utilizador.
 */
@Entity
@Table(name = "multas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Multa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String motivo;
    private String descricao;
    private double valor;
    private LocalDateTime data;


    public Multa(String motivo, String descricao) {
        this.motivo = motivo;
        this.descricao = descricao;
        this.valor = 100.0;
        this.data = LocalDateTime.now();
    }
}