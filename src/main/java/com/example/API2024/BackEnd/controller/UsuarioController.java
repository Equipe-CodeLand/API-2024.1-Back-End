package com.example.API2024.BackEnd.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.API2024.BackEnd.dto.ManutencaoUpdateDto;
import com.example.API2024.BackEnd.dto.UsuarioDto;
import com.example.API2024.BackEnd.dto.UsuarioUpdateDto;
import com.example.API2024.BackEnd.model.Ativos;
import com.example.API2024.BackEnd.model.Manutencao;
import com.example.API2024.BackEnd.model.Usuario;
import com.example.API2024.BackEnd.repository.UsuarioRepository;
import com.example.API2024.BackEnd.service.UsuarioService;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(value = "/usuario")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@PostMapping("/cadastrar")
	public void cadastrarUsuario(@RequestBody UsuarioDto usuario) throws Exception { 
		usuarioService.cadastrarUsuario(usuario);
	}

	@GetMapping("/listar")
	public List<Usuario> listarUsuarios() { return usuarioRepository.findAll(); }

	@GetMapping("/{cpf}/cargo")
	public String getCargo(@PathVariable String cpf) throws Exception {
		return usuarioService.buscarUsuarioPorCpf(cpf).getCargo().getNome();
	}
	@PutMapping("{id}/inativar")
	public ResponseEntity<String> inativarUsuario(@PathVariable Long id) throws Exception {
		return usuarioService.inativarUsuario(id);
	}
	@PutMapping("{id}/ativar")
	public ResponseEntity<String> ativarUsuario(@PathVariable Long id) throws Exception {
		return usuarioService.ativarUsuario(id);
	}
	
	@PutMapping("/atualizar/usuario/{id}")
	public Usuario atualizar(@RequestBody UsuarioUpdateDto usuariodto, @PathVariable Long id) throws Exception {
		return usuarioService.atualizar(id, usuariodto);
	}
}



