package com.example.API2024.BackEnd.dto;

import java.util.List;

import com.example.API2024.BackEnd.model.Ativos;

import lombok.Data;

@Data
public class DashboardDto {

	private List<Ativos> ativos;
	
	private List<Ativos> ativosExpirando;
		
	private List<Integer> qntManutencoes;
	
	private List<Double> valorPorStatus;
	
	private List<Integer> ativosPorStatus;
}
