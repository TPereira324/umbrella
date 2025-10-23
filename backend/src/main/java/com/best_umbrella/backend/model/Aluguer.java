package com.best_umbrella.backend.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "aluguer")
public class Aluguer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 🔹 Identifica o utilizador que fez o aluguer
    @Column(nullable = false)
    private Long utilizadorId;

    // 🔹 Identifica o guarda-chuva alugado
    @Column(nullable = false)
    private Long guardaChuvaId;

    // 🔹 Data e hora em que o aluguer começou
    @Column(nullable = false)
    private LocalDateTime dataInicio;

    // 🔹 Data e hora em que o guarda-chuva foi devolvido (pode ser null)
    private LocalDateTime dataFim;

    // 🔹 Indica se já foi aplicada uma multa por atraso
    private boolean multado = false;

    // 🔹 Valor da multa, se existir
    private double valorMulta = 0.0;

    // 🔹 Estado do aluguer
    @Column(length = 50)
    private String estado = "Ativo"; // pode ser "Ativo", "Concluído" ou "Multado"

    public Aluguer() {}

    public Aluguer(Long utilizadorId, Long guardaChuvaId) {
        this.utilizadorId = utilizadorId;
        this.guardaChuvaId = guardaChuvaId;
        this.dataInicio = LocalDateTime.now();
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

    public Long getGuardaChuvaId() {
        return guardaChuvaId;
    }

    public void setGuardaChuvaId(Long guardaChuvaId) {
        this.guardaChuvaId = guardaChuvaId;
    }

    public LocalDateTime getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDateTime dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDateTime getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDateTime dataFim) {
        this.dataFim = dataFim;
    }

    public boolean isMultado() {
        return multado;
    }

    public void setMultado(boolean multado) {
        this.multado = multado;
    }

    public double getValorMulta() {
        return valorMulta;
    }

    public void setValorMulta(double valorMulta) {
        this.valorMulta = valorMulta;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    // ======================
    // 🔹 Métodos úteis
    // ======================

    public boolean precisaDeMulta() {
        if (dataFim == null) {
            LocalDateTime agora = LocalDateTime.now();
            return dataInicio.plusHours(24).isBefore(agora);
        }
        return false;
    }

    public void aplicarMulta() {
        this.multado = true;
        this.valorMulta = 100.0;
        this.estado = "Multado";
    }
}
