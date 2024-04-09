package com.example.API2024.BackEnd.controller;

import java.util.List;

import com.example.API2024.BackEnd.model.dto.ManutencaoUpdateDto;
import com.example.API2024.BackEnd.repository.ManutencaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import com.example.API2024.BackEnd.model.Manutencao;
import com.example.API2024.BackEnd.service.ManutencaoService;

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
		Manutencao manutencao = manutencaoRepositorio.findById(id).get();
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

}
