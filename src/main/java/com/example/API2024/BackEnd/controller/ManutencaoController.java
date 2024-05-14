package com.example.API2024.BackEnd.controller;


import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import com.example.API2024.BackEnd.dto.AtivosDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.API2024.BackEnd.dto.ManutencaoDto;
import com.example.API2024.BackEnd.dto.ManutencaoUpdateDto;
import com.example.API2024.BackEnd.model.Manutencao;
import com.example.API2024.BackEnd.repository.ManutencaoRepository;
import com.example.API2024.BackEnd.service.ManutencaoService;


@CrossOrigin(origins = "http://localhost:3000" )
@RestController
@RequestMapping(value = "/manutencao")
public class ManutencaoController {
	
	@Autowired
	private ManutencaoService manutencaoService;

	@Autowired
	private ManutencaoRepository manutencaoRepositorio;


	@GetMapping
	public List<Manutencao> findAll(){
		return manutencaoService.findAll();
	}

	@GetMapping("/{id}")
	public Manutencao findById(@PathVariable Long id) {
		return manutencaoService.findById(id);
	}

	@PutMapping("/{id}")
	public Manutencao atualizarManutencao(@RequestBody ManutencaoUpdateDto manutencaodto, @PathVariable Long id) {
		return manutencaoService.updateManutencao(manutencaodto, id);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletarManutencao(@PathVariable Long id) {
		if (manutencaoRepositorio.existsById(id)) {
			manutencaoRepositorio.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/cadastrar/{id}")
	public Manutencao cadastrarManutencao(@RequestBody ManutencaoDto manutencao, @PathVariable Long id) {
		return manutencaoService.cadastrarManutencao(manutencao.toEntity(), id);
	}
	
	@GetMapping("/filtrar/{nomeAtivo}")
	public List<Manutencao> filtrarPorNomeAtivo(@PathVariable String nomeAtivo) {
		String nomeAtivoFormatado = nomeAtivo.replace(" ", "%20");
		return manutencaoService.filtrarPorNomeAtivo(nomeAtivoFormatado);
	}
	
	@GetMapping("/filtrar/dataInicio")
	public List<Manutencao> filtrarPorDataInicio(@RequestParam("dataInicio") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataInicio) {
		return manutencaoService.filtrarPorDataInicio(dataInicio);
	}
	
	@GetMapping("/filtrar/dataFinal")
	public List<Manutencao> filtrarPorDataFinal(@RequestParam("dataFinal") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataFinal) {
		return manutencaoService.filtrarPorDataFinal(dataFinal);
	}
	
}