package com.example.API2024.BackEnd.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Data
@Entity
public class Funcionario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String nome_funcionario;

	@Column
	private double cpf;

	{/*
		@ManyToOne
		@JoinColumn(name = "turno_id")
		private Turno turno;
	*/}

	@ManyToOne
	@JoinColumn(name = "setor_id")
	private Setor setor;

	@OneToMany(mappedBy="funcionario",  fetch = FetchType.EAGER)
	@JsonBackReference
	private List<Ativos> ativos = new ArrayList<>();
}
