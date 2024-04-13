package com.example.API2024.BackEnd.dto;

import java.sql.Date;
import java.time.LocalDate;

import lombok.Data;


@Data
public class ManutencaoUpdateDto {

	private String localizacao;

	private String responsavel;

	private String data_inicio;

	private String data_final;

	private Long ativos_id;

}
