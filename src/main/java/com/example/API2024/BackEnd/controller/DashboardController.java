package com.example.API2024.BackEnd.controller;


import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.API2024.BackEnd.dto.DashboardDto;
import com.example.API2024.BackEnd.repository.AtivosRepository;
import com.example.API2024.BackEnd.service.AtivosService;
import com.example.API2024.BackEnd.service.ManutencaoService;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(value = "/dashboard")
public class DashboardController {
	
	@Autowired
	private AtivosService ativosService;
	
	@Autowired
	private ManutencaoService manutencaoService;
	
	@Autowired
	private AtivosRepository ativosRepository;
	
	@GetMapping("/")
	public DashboardDto buscarRelatorio() {
		DashboardDto dashboard = new DashboardDto();
        LocalDate dataHoje = LocalDate.now();
		dashboard.setAtivos(ativosRepository.findAll());
		dashboard.setAtivosPorStatus(ativosService.buscarQntAtivosPorStatus(dashboard.getAtivos()));
		dashboard.setValorPorStatus(ativosService.buscarValorTotalPorStatus(dashboard.getAtivos()));
		dashboard.setAtivosExpirando(ativosService.filtrarPorExpiracaoProxima(dashboard.getAtivos(), dataHoje, 60));
		dashboard.setQntManutencoes(manutencaoService.buscarQntManutencoesProximos(
				manutencaoService.filtrarPorDataInicio(dataHoje), dataHoje));
		return dashboard;
	}

	@GetMapping("/{dataInicio}/{dataFinal}")
	public DashboardDto buscarRelatorioComData(@PathVariable LocalDate dataInicio, @PathVariable LocalDate dataFinal) { 
		DashboardDto dashboard = new DashboardDto();
		dashboard.setAtivos(ativosService.filtrarPorDataInicioEDataFinal(dataInicio, dataFinal));
		dashboard.setAtivosPorStatus(ativosService.buscarQntAtivosPorStatus(dashboard.getAtivos()));
		dashboard.setValorPorStatus(ativosService.buscarValorTotalPorStatus(dashboard.getAtivos()));
		dashboard.setAtivosExpirando(ativosService.filtrarPorExpiracaoProxima(dashboard.getAtivos(), dataFinal, 60));
		dashboard.setQntManutencoes(manutencaoService.buscarQntManutencoesProximos(
				manutencaoService.filtrarPorDataInicioEDataFinal(dataInicio, dataFinal), dataInicio));
		return dashboard;
		}


}



