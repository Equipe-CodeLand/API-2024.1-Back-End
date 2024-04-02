package com.example.API2024.BackEnd.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Funcionario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String nomeFuncionario;

	@Column
	private double cpf;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "turno_id")
	private Turno turno;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "setor_id")
	private Setor setor;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "ativos_id")
	private List<Ativos> ativos = new ArrayList<>();
}
