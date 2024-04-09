package com.example.API2024.BackEnd.model;

import com.example.API2024.BackEnd.model.dto.ManutencaoUpdateDto;
import com.example.API2024.BackEnd.repository.AtivosRepository;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

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

	public Manutencao update(ManutencaoUpdateDto manutencao, Ativos ativos) {
		this.setLocalizacao(manutencao.getLocalizacao());
		this.setResponsavel(manutencao.getResponsavel());
		this.setData_inicio(manutencao.getData_inicio());
		this.setData_final(manutencao.getData_final());
	    this.setAtivos(ativos);
		return this;
	}
}
