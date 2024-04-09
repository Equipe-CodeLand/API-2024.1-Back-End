package com.example.API2024.BackEnd.model.dto;

import com.example.API2024.BackEnd.model.Manutencao;
import com.example.API2024.BackEnd.repository.AtivosRepository;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

@Data
public class ManutencaoUpdateDto {
	@JsonIgnore
	@Autowired
	private AtivosRepository repositorio;

	private String localizacao;

	private String responsavel;

	private Date data_inicio;

	private Date data_final;

	private Long ativos_id;

	public Manutencao toEntity() {
		Manutencao manutencao = new Manutencao();
		manutencao.setLocalizacao(localizacao);
		manutencao.setResponsavel(responsavel);
		manutencao.setData_inicio(data_inicio);
		manutencao.setData_final(data_final);

		return manutencao;
	}
}
