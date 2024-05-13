package com.example.API2024.BackEnd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.example.API2024.BackEnd.dto.AlteracaoSenhaDto;
import com.example.API2024.BackEnd.model.Credencial;
import com.example.API2024.BackEnd.model.Usuario;
import com.example.API2024.BackEnd.repository.CredencialRepository;
import com.example.API2024.BackEnd.service.CredencialService;
import com.example.API2024.BackEnd.service.UsuarioService;
import org.springframework.http.HttpStatus;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class CredencialController {
	
	@Autowired
	CredencialRepository credencialRepository;
	
	@Autowired
	CredencialService credencialService;
	
	@Autowired
	UsuarioService usuarioService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PutMapping("/credencial/{cpf}/senha")
	public ResponseEntity<String> alterarSenha(@PathVariable String cpf) {
		try {
			Usuario usuario = usuarioService.buscarUsuarioPorCpf(cpf);
			credencialService.enviarCodigoVerificacao(cpf, usuario.getEmail());
			return ResponseEntity.ok("Código de verificação enviado com sucesso!");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@PutMapping("/credencial/{cpf}/senha/{codigo}")
	public ResponseEntity<String> confirmarAlteracaoSenha(@PathVariable String cpf, @PathVariable String codigo, @RequestBody AlteracaoSenhaDto alteracaoSenhaDto) {
		try {
			Usuario usuario = usuarioService.buscarUsuarioPorCpf(cpf);
			credencialService.alterarSenha(cpf, alteracaoSenhaDto.getNovaSenha(), usuario.getEmail(), codigo);
			return ResponseEntity.ok("Senha alterada com sucesso!");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
}
