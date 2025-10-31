package com.best_umbrella.backend.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Notificacao")

public class Noti {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notificacao_id")
    private Long notificacaoId;

    @ManyToOne
    @JoinColumn(name = "utilizador_id")
    private utilizador utilizador;

    private String mensagem;
    private String tipo;

    @Column(name = "data_envio")
    private LocalDateTime dataEnvio;

    private String estado;

    // Getters e Setters
}
