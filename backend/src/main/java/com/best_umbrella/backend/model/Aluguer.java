package com.best_umbrella.backend.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Aluguer" )


public class Aluguer
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "aluguer_id")
    private Long aluguerId;

    @ManyToOne
    @JoinColumn(name = "utilizador_id")
    private utilizador utilizador;

    @ManyToOne
    @JoinColumn(name = "guarda_chuva_id")
    private guardaChuva guardaChuva;

    @ManyToOne
    @JoinColumn(name = "ponto_inicio_id")
    private PontodeAluguer pontoInicio;

    @ManyToOne
    @JoinColumn(name = "ponto_fim_id")
    private PontodeAluguer  pontoFim;

    @Column(name = "data_inicio")
    private LocalDateTime dataInicio;

    @Column(name = "data_fim")
    private LocalDateTime dataFim;

    private Double custo;
    private String estado;

    // Getters e Setters

}
