package com.example.API2024.BackEnd.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.API2024.BackEnd.model.Manutencao;
import com.example.API2024.BackEnd.service.ManutencaoService;

@RestController
@RequestMapping(value = "/manutencao")
public class ManutencaoController {
	
	@Autowired
	private ManutencaoService manutencaoService;
	
	@GetMapping
	public List<Manutencao> findAll(){
		return manutencaoService.findAll();
	}
	@GetMapping("/{id}")
	public Manutencao findById(@PathVariable Long id) {
		return manutencaoService.findById(id);
	}

}
