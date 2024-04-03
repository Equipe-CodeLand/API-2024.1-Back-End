package com.example.API2024.BackEnd.controller;

import com.example.API2024.BackEnd.model.Administrador;
import com.example.API2024.BackEnd.repository.AdmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdmController {

	@Autowired
	public AdmRepository repositorio;

	@PostMapping("/cadastro/adm")
	public void cadastrarAdm(@RequestBody Administrador adm) {
		repositorio.save(adm);
	}

}
