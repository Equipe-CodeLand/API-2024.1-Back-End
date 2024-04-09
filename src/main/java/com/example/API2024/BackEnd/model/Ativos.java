package com.example.API2024.BackEnd.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.example.API2024.BackEnd.model.dto.AtivosDto;
import com.fasterxml.jackson.annotation.JsonBackReference;

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
	private double precoAquisicao;

	@Column
	private String modelo;

	@Column
	private String marca;
	
	@Column
	private Date dataAquisicao;
	
	@Column
	private Date dataExpiracao;

	@OneToMany(mappedBy = "ativos")
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
		this.setPrecoAquisicao(ativos.getPrecoAquisicao());
		this.setModelo(ativos.getModelo());
		this.setMarca(ativos.getMarca());
		this.setStatus(ativos.getStatus());
		this.setDataAquisicao(ativos.getDataAquisicao());
		this.setDataExpiracao(ativos.getDataExpiracao());
		this.setFuncionario(ativos.getFuncionario());
		
		return this;
	}
}
