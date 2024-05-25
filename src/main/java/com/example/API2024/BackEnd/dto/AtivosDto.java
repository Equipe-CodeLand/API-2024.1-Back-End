package com.example.API2024.BackEnd.dto;

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
	
	private String codigo_nota_fiscal;

	public Ativos toEntity() {
		Ativos ativos = new Ativos();
		ativos.setNome(nome);
		ativos.setDescricao(descricao);
		ativos.setPreco_aquisicao(preco_aquisicao);
		ativos.setModelo(modelo);
		ativos.setMarca(marca);
		ativos.setStatus(status);
		ativos.setUsuario(usuario);
		ativos.setCodigo_nota_fiscal(codigo_nota_fiscal);
		ativos.setDataAquisicao(LocalDate.parse(dataAquisicao));

		if (dataExpiracao != null && !dataExpiracao.isEmpty()) {
			ativos.setDataExpiracao(LocalDate.parse(dataExpiracao));
		} else {
			ativos.setDataExpiracao(null);
		}

		return ativos;
	}
}