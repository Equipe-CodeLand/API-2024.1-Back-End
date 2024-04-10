package com.example.API2024.BackEnd.model.dto;

import java.sql.Date;

import com.example.API2024.BackEnd.model.Ativos;
import com.example.API2024.BackEnd.model.Status;

import lombok.Data;

@Data
public class AtivosDto {
	
	private String nome;

	private String descricao;

	private double precoAquisicao;

	private String modelo;

	private String marca;
	
	private Status status;
	
	private Date dataAquisicao;
	
	private Date dataExpiracao;
	
	private String funcionario;
	
	public Ativos toEntity() {
		Ativos ativos = new Ativos();
		ativos.setNome(nome);
		ativos.setDescricao(descricao);
		ativos.setPreco_aquisicao(precoAquisicao);
		ativos.setModelo(modelo);
		ativos.setMarca(marca);
		ativos.setStatus(status);
		ativos.setDataAquisicao(dataAquisicao);
		ativos.setDataExpiracao(dataExpiracao);
		ativos.setFuncionario(funcionario);
		
		return ativos;
	}
}
