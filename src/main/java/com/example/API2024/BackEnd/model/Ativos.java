package com.example.API2024.BackEnd.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Ativos {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String nome;

	@Column
	private String descricao;

	@Column
	private double precoAquisicao;

	@Column
	private String modelo;

	@Column
	private String marca;

	@OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
	@JoinColumn(name="manutencao_id")
	private List<Manutencao> manutencao = new ArrayList<>();

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name="status_id")
	private Status status;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="setor_id")
	private Setor setor;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="func_id")
	private Funcionario funcionario;
}
