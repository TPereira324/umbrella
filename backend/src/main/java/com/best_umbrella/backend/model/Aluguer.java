package com.best_umbrella.backend.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Aluguer")
public class Aluguer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "aluguer_id")
    private Long aluguerId;

    @ManyToOne
    @JoinColumn(name = "utilizador_id")
    private Utilizador utilizador;

    @ManyToOne
    @JoinColumn(name = "guarda_chuva_id")
    private GuardaChuva guardaChuva;

    @ManyToOne
    @JoinColumn(name = "ponto_inicio_id")
    private PontodeAluguer pontoInicio;

    @ManyToOne
    @JoinColumn(name = "ponto_fim_id")
    private PontodeAluguer pontoFim;

    @Column(name = "data_inicio")
    private LocalDateTime dataInicio;

    @Column(name = "data_fim")
    private LocalDateTime dataFim;


    // return Date
    private Double custo;
    private String estado;

    // Getters e Setters
    public Long getAluguerId() {
        return aluguerId;
    }

    public void setAluguerId(Long aluguerId) {
        this.aluguerId = aluguerId;
    }

    public Utilizador getUtilizador() {
        return utilizador;
    }

    public void setUtilizador(Utilizador utilizador) {
        this.utilizador = utilizador;
    }

    public GuardaChuva getGuardaChuva() {
        return guardaChuva;
    }

    public void setGuardaChuva(GuardaChuva guardaChuva) {
        this.guardaChuva = guardaChuva;
    }

    public PontodeAluguer getPontoInicio() {
        return pontoInicio;
    }

    public void setPontoInicio(PontodeAluguer pontoInicio) {
        this.pontoInicio = pontoInicio;
    }

    public PontodeAluguer getPontoFim() {
        return pontoFim;
    }

    public void setPontoFim(PontodeAluguer pontoFim) {
        this.pontoFim = pontoFim;
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

    public Double getCusto() {
        return custo;
    }

    public void setCusto(Double custo) {
        this.custo = custo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
