package com.best_umbrella.backend.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Guarda_chuva")
public class GuardaChuva {
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
    
    @OneToMany(mappedBy = "guardaChuva")
    private List<Aluguer> alugueres;

    // Getters e Setters
    public Long getGuardaChuvaId() {
        return guardaChuvaId;
    }

    public void setGuardaChuvaId(Long guardaChuvaId) {
        this.guardaChuvaId = guardaChuvaId;
    }

    public String getCodigoQr() {
        return codigoQr;
    }

    public void setCodigoQr(String codigoQr) {
        this.codigoQr = codigoQr;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public LocalDateTime getDataRegisto() {
        return dataRegisto;
    }

    public void setDataRegisto(LocalDateTime dataRegisto) {
        this.dataRegisto = dataRegisto;
    }

    public PontodeAluguer getPontodeAluguer() {
        return pontodeAluguer;
    }

    public void setPontodeAluguer(PontodeAluguer pontodeAluguer) {
        this.pontodeAluguer = pontodeAluguer;
    }

    public List<Aluguer> getAlugueres() {
        return alugueres;
    }

    public void setAlugueres(List<Aluguer> alugueres) {
        this.alugueres = alugueres;
    }
}
