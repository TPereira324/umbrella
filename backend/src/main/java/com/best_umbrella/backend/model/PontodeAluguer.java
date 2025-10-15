package com.best_umbrella.backend.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "PontodeAluguer")

public class PontodeAluguer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ponto_id")
    private Long pontoId;

    private String nome;
    private Double latitude;
    private Double longitude;
    private Integer capacidade;
    private String tipo;

    @OneToMany(mappedBy = "pontoAluguer")
    private List<guardaChuva> guardaChuvas;

    // Getters e Setters
}
