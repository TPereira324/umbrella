package com.best_umbrella.backend.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Guarda_chuva")


public class guardaChuva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "guarda_chuva_id")
    private Long guardaChuvaId;

    @Column(name = "codigo_qr", unique = true, nullable = false)
    private String codigoQr;

    private String estado;
    private String cor;
    private String tipo;

    @Column(name = "data_registo")
    private LocalDateTime dataRegisto;

    @ManyToOne
    @JoinColumn(name = "ponto_id")
    private PontodeAluguer pontodeAluguer;


    // Getters e Setters

}
