package com.example.API2024.BackEnd.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.example.API2024.BackEnd.dto.ManutencaoUpdateDto;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Data
@NoArgsConstructor
public class Manutencao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private LocalDate data_inicio;

	@Column
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private LocalDate data_final;
	
	@Column
	private String descricao;

	@Column
	private String localizacao;

	@Column
	private String responsavel;

	@ManyToOne
	@JoinColumn(name = "ativos_id")
	@JsonManagedReference
	private Ativos ativos;

	public Manutencao update(ManutencaoUpdateDto manutencao, Ativos ativos) {
		this.setLocalizacao(manutencao.getLocalizacao());
		this.setResponsavel(manutencao.getResponsavel());
		this.setData_inicio(LocalDate.parse(manutencao.getData_inicio()));
		this.setData_final(LocalDate.parse(manutencao.getData_final()));
	    this.setAtivos(ativos);
		return this;
	}
}
