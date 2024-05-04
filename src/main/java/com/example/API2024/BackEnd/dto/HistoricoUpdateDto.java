package com.example.API2024.BackEnd.dto;

import com.example.API2024.BackEnd.model.Usuario;
import lombok.Data;

import java.time.LocalDate;

@Data
public class HistoricoUpdateDto {
	private Long ativoId;
	private LocalDate dataCadastro;
	private Usuario usuario;
}
