package com.example.API2024.BackEnd.model;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
import java.util.List;

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
