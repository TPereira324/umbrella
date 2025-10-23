package com.best_umbrella.backend.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "pontos_aluguer")
public class PontodeAluguer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private double latitude;
    private double longitude;
    private int totalGuardaChuvas;

    @OneToMany(mappedBy = "pontoAluguer", cascade = CascadeType.ALL)
    private List<GuardaChuva> guardaChuvas;

    public PontodeAluguer() {
    }

    public PontodeAluguer(String nome, double latitude, double longitude, int totalGuardaChuvas) {
        this.nome = nome;
        this.latitude = latitude;
        this.longitude = longitude;
        this.totalGuardaChuvas = totalGuardaChuvas;
    }

    // ========= GETTERS & SETTERS =========
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
