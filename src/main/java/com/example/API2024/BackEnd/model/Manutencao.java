package com.example.API2024.BackEnd.model;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonManagedReference;

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
	private Date data_inicio;

	@Column
	private Date data_final;

	@Column
	private String localizacao;

	@Column
	private String responsavel;

	@ManyToOne
	@JoinColumn(name = "ativos_id")
	@JsonManagedReference
	private Ativos ativos;
}
