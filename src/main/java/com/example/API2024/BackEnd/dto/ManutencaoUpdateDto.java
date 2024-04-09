package com.example.API2024.BackEnd.dto;

import java.sql.Date;

import lombok.Data;


@Data
public class ManutencaoUpdateDto {

	private String localizacao;

	private String responsavel;

	private Date data_inicio;

	private Date data_final;

	private Long ativos_id;


}
