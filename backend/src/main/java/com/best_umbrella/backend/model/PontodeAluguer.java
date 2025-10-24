package com.best_umbrella.backend.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "pontos_aluguer")
public class PontodeAluguer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ponto_id")
    private Integer pontoId;

    private String nome;
    private double latitude;
    private double longitude;
    private int totalGuardaChuvas;

    @OneToMany(mappedBy = "pontoAluguer", cascade = CascadeType.ALL)
    private List<GuardaChuva> guardaChuvas;
    
    @OneToMany(mappedBy = "pontoInicio")
    private List<Aluguer> alugueresInicio;
    
    @OneToMany(mappedBy = "pontoFim")
    private List<Aluguer> alugueresTermino;

    // Getters e Setters
    public Integer getPontoId() {
        return pontoId;
    }

    public void setPontoId(Integer pontoId) {
        this.pontoId = pontoId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public int getTotalGuardaChuvas() {
        return totalGuardaChuvas;
    }

    public void setTotalGuardaChuvas(int totalGuardaChuvas) {
        this.totalGuardaChuvas = totalGuardaChuvas;
    }

    public List<GuardaChuva> getGuardaChuvas() {
        return guardaChuvas;
    }

    public void setGuardaChuvas(List<GuardaChuva> guardaChuvas) {
        this.guardaChuvas = guardaChuvas;
    }
}
