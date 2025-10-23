package com.best_umbrella.backend.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "guarda_chuvas")
public class GuardaChuva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String codigo;
    private boolean disponivel = true;

    @ManyToOne
    @JoinColumn(name = "ponto_id")
    private PontodeAluguer pontoAluguer;

    @OneToMany(mappedBy = "guardaChuva", cascade = CascadeType.ALL)
    private List<Aluguer> alugueres;

    public GuardaChuva() {
    }

    public GuardaChuva(String codigo, PontodeAluguer pontoAluguer) {
        this.codigo = codigo;
        this.pontoAluguer = pontoAluguer;
    }

    // ========= GETTERS & SETTERS =========
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public PontodeAluguer getPontoAluguer() {
        return pontoAluguer;
    }

    public void setPontoAluguer(PontodeAluguer pontoAluguer) {
        this.pontoAluguer = pontoAluguer;
    }

    public List<Aluguer> getAlugueres() {
        return alugueres;
    }

    public void setAlugueres(List<Aluguer> alugueres) {
        this.alugueres = alugueres;
    }
}
