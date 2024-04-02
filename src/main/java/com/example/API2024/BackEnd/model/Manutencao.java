package com.example.API2024.BackEnd.model;

import java.sql.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Manutencao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private Date dataInicio;

	@Column
	private Date dataFinal;

	@Column
	private String localizacao;

	@Column
	private String responsavel;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ativos_id")
	private Ativos ativos;
}
