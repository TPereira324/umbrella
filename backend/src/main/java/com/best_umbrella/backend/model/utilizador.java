package com.best_umbrella.backend.model;


import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;

import javax.management.Notification;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table
public class utilizador
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Utilizador")
    private Long utilizadorId;

    private String Nome;

    @NotNull
    private String email;

    @NotNull
    private String password;
    private String telefone;

    @Column(name = "date_registro")
    private LocalDateTime dataRegisto;

    private Double rating;

    @OneToMany(mappedBy = "utilizador")
    private List<Aluguer> alugures;

    @OneToMany(mappedBy = "utilizador")
    private List<Notification> notifications;

    //Getters e Setters



}
