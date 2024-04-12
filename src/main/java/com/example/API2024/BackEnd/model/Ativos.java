package com.example.API2024.BackEnd.model;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.example.API2024.BackEnd.model.dto.AtivosDto;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Ativos {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String nome;

	@Column
	private String descricao;

	@Column
	private double preco_aquisicao;

	@Column
	private String modelo;

	@Column
	private String marca;
	
	@Column
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private LocalDate dataAquisicao;

	@Column
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private LocalDate dataExpiracao;
	
	@Column
	private String nome_funcionario;

	@OneToMany(mappedBy = "ativos", cascade = CascadeType.REMOVE)
	@JsonBackReference
	private List<Manutencao> manutencao = new ArrayList<>();

	@ManyToOne
	@JoinColumn(name="status_id")
	private Status status;

	@ManyToOne
	@JoinColumn(name="setor_id")
	private Setor setor;

	@Column
	private String funcionario;
	
	public Ativos update(AtivosDto ativos){
		this.setNome(ativos.getNome());
		this.setDescricao(ativos.getDescricao());
		this.setPreco_aquisicao(ativos.getPreco_aquisicao());
		this.setModelo(ativos.getModelo());
		this.setMarca(ativos.getMarca());
		this.setStatus(ativos.getStatus());
		this.setDataAquisicao(LocalDate.parse(ativos.getDataAquisicao()));
		this.setDataExpiracao(LocalDate.parse(ativos.getDataExpiracao()));
		this.setFuncionario(ativos.getFuncionario());
		
		return this;
	}
	{/*
		@ManyToOne()
		@JoinColumn(name = "func_id")
		@JsonManagedReference
		private Funcionario funcionario;
	*/}
}
