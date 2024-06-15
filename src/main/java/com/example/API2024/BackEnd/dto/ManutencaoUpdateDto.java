package com.example.API2024.BackEnd.dto;

import lombok.Data;


@Data
public class ManutencaoUpdateDto {

	private String localizacao;

	private String responsavel;
	
	private String descricao;

	private String data_inicio;

	private String data_final;

	private Long ativos_id;

}
