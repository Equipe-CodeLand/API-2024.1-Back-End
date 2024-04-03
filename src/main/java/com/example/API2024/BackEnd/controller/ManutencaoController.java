package com.example.API2024.BackEnd.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.API2024.BackEnd.model.Manutencao;
import com.example.API2024.BackEnd.service.ManutencaoService;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@CrossOrigin(origins = "http://localhost:3000")
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

    @PostMapping("/manutencaoCadastro")
    public Manutencao createManutencao(@RequestBody Manutencao manutencao) {
        return manutencaoService.cadastrarManutencao(manutencao);

}}


