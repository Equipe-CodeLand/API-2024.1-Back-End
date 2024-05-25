package com.example.API2024.BackEnd.dto;

import lombok.Data;

@Data
public class UsuarioUpdateDto {
	private String nome;
	private Long cargo;
	private String cpf;
	private String email;
}
