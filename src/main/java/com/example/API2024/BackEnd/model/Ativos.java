package com.example.API2024.BackEnd.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

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

	@OneToMany(mappedBy = "ativos")
	@JsonBackReference
	private List<Manutencao> manutencao = new ArrayList<>();

	@ManyToOne
	@JoinColumn(name="status_id")
	private Status status;

	@ManyToOne
	@JoinColumn(name="setor_id")
	private Setor setor;

	@ManyToOne()
	@JoinColumn(name="func_id")
	@JsonManagedReference
	private Funcionario funcionario;
}
