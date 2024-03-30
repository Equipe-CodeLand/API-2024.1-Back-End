package com.example.API2024.BackEnd.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Manutencao {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nomeAtivo;

    @Column(nullable = false)
    private String nomeFuncionario;

    @Column(nullable = false)
    private Date dataInicio;

    @Column(nullable = false)
    private Date dataTermino;
}
