package com.best_umbrella.backend.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "utilizadores")
public class Utilizador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;
    private String password;
    private double saldo = 0.0;
    private int pontosFidelizacao = 0;

    @OneToMany(mappedBy = "utilizador", cascade = CascadeType.ALL)
    private List<Aluguer> alugueres;

    public Utilizador() {
    }

    public Utilizador(String nome, String email, String password) {
        this.nome = nome;
        this.email = email;
        this.password = password;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public int getPontosFidelizacao() {
        return pontosFidelizacao;
    }

    public void setPontosFidelizacao(int pontosFidelizacao) {
        this.pontosFidelizacao = pontosFidelizacao;
    }

    public List<Aluguer> getAlugueres() {
        return alugueres;
    }

    public void setAlugueres(List<Aluguer> alugueres) {
        this.alugueres = alugueres;
    }
}
