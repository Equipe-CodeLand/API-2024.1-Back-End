package com.example.API2024.BackEnd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.API2024.BackEnd.model.Usuario;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.API2024.BackEnd.dto.UsuarioDto;
import com.example.API2024.BackEnd.model.Cargo;
import com.example.API2024.BackEnd.model.Credencial;
import com.example.API2024.BackEnd.model.Usuario;
import com.example.API2024.BackEnd.repository.CargoRepository;

import com.example.API2024.BackEnd.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;	
	@Autowired
	private CargoService cargoService;	
	@Autowired
	private CredencialService credencialService;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public Usuario buscarUsuarioPorId(Long id) throws Exception {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new Exception("Usuário não encontrado"));
        return usuario;
	}
	
	public Usuario buscarUsuarioPorCpf(String cpf) throws Exception {
		return usuarioRepository.findByCredencialCpf(cpf).orElseThrow(() -> new Exception("Usuário não encontrado"));
	}		
	
	public void cadastrarUsuario(UsuarioDto usuarioDto) throws Exception {
		Usuario usuario = usuarioDto.toEntity();
		Cargo cargo = cargoService.buscarCargoPorId(usuarioDto.getCargo());
		usuario.setCargo(cargo);
		Credencial credencial = credencialService.criarCredencial(usuarioDto.getCpf(), usuarioDto.getSenha());
		usuario.setCredencial(credencial);
		usuario.setEstaAtivo(true);
		usuarioRepository.save(usuario);
	}

	public ResponseEntity<String> inativarUsuario(Long id) throws Exception {
		Usuario usuario = buscarUsuarioPorId(id);
		usuario.setEstaAtivo(false);
		usuario = usuarioRepository.save(usuario);
		if( !usuario.isEstaAtivo()) {
			return ResponseEntity.ok("Usuario inativado");
		} else {
			return ResponseEntity.badRequest().build();
		}
	}

	public ResponseEntity<String> ativarUsuario(Long id) throws Exception {
		Usuario usuario = buscarUsuarioPorId(id);
		usuario.setEstaAtivo(true);
		usuario = usuarioRepository.save(usuario);
		if( usuario.isEstaAtivo()) {
			return ResponseEntity.ok("Usuario ativado");
		} else {
			return ResponseEntity.badRequest().build();
		}
	}

	
}

