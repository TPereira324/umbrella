package com.best_umbrella.backend.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "notificacao")
public class Noti {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 🔹 ID do utilizador que recebe a notificação
    @Column(nullable = false)
    private Long utilizadorId;

    // 🔹 Título da notificação
    @Column(nullable = false, length = 100)
    private String titulo;

    // 🔹 Mensagem completa
    @Column(nullable = false, length = 255)
    private String mensagem;

    // 🔹 Data e hora de envio
    @Column(nullable = false)
    private LocalDateTime dataEnvio;

    // 🔹 Indica se o utilizador já viu a notificação
    private boolean lida = false;

    public Noti() {}

    public Noti(Long utilizadorId, String titulo, String mensagem) {
        this.utilizadorId = utilizadorId;
        this.titulo = titulo;
        this.mensagem = mensagem;
        this.dataEnvio = LocalDateTime.now();
    }

    // ======================
    // 🔹 GETTERS e SETTERS
    // ======================

    public Long getId() {
        return id;
    }

    public Long getUtilizadorId() {
        return utilizadorId;
    }

    public void setUtilizadorId(Long utilizadorId) {
        this.utilizadorId = utilizadorId;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public LocalDateTime getDataEnvio() {
        return dataEnvio;
    }

    public void setDataEnvio(LocalDateTime dataEnvio) {
        this.dataEnvio = dataEnvio;
    }

    public boolean isLida() {
        return lida;
    }

    public void setLida(boolean lida) {
        this.lida = lida;
    }
}
