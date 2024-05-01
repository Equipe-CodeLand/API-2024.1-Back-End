package com.example.API2024.BackEnd.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
@Entity
public class Historico {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@ManyToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "ativo_id")
	private Ativos ativo;

	@Column
	private LocalDate data_cadastro;

	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;
}
