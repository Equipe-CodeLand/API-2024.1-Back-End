package com.example.API2024.BackEnd.dto;

import java.sql.Date;
import java.time.LocalDate;

import com.example.API2024.BackEnd.model.Ativos;
import com.example.API2024.BackEnd.model.Status;
import com.example.API2024.BackEnd.model.Usuario;

import lombok.Data;

@Data
public class AtivosDto {
	
	private String nome;

	private String descricao;

	private double preco_aquisicao;

	private String modelo;

	private String marca;
	
	private Status status;
	
	private Usuario usuario;
	
	private String dataAquisicao;
	
	private String dataExpiracao;
	
	public Ativos toEntity() {
		Ativos ativos = new Ativos();
		ativos.setNome(nome);
		ativos.setDescricao(descricao);
		ativos.setPreco_aquisicao(preco_aquisicao);
		ativos.setModelo(modelo);
		ativos.setMarca(marca);
		ativos.setStatus(status);
		ativos.setUsuario(usuario);
		ativos.setDataAquisicao(LocalDate.parse(dataAquisicao));
		ativos.setDataExpiracao(LocalDate.parse(dataExpiracao));
		
		return ativos;
	}
}
