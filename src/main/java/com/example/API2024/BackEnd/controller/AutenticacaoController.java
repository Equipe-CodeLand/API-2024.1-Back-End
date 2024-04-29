package com.example.API2024.BackEnd.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.API2024.BackEnd.dto.AutenticacaoDto;
import com.example.API2024.BackEnd.service.AutenticacaoService;

@RestController
@CrossOrigin
public class AutenticacaoController {
		
		@Autowired
		private AutenticacaoService autenticacaoService;
		
		/* @Autowired
		private JwtFiltroAutenticador filtroAutenticador; */

	@PostMapping(value = "/login")
		public ResponseEntity<?> login(@RequestBody AutenticacaoDto autenticacaoDto){
			return ResponseEntity.ok(autenticacaoService.login(autenticacaoDto));
		}
}
