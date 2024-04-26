package com.example.API2024.BackEnd.controller;

import com.example.API2024.BackEnd.model.Historico;
import com.example.API2024.BackEnd.repository.HistoricoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HistoricoController {

	@Autowired
	public HistoricoRepository historicoRepository;

	@GetMapping("/listar/historico/{id}")
	public List<Historico> listaHistorico(@PathVariable Long id) {
		return historicoRepository.findByAtivoId(id);
	}
}
