package com.example.API2024.BackEnd.service;

import java.util.Optional;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.API2024.BackEnd.model.Cargo;
import com.example.API2024.BackEnd.model.Credencial;
import com.example.API2024.BackEnd.model.Usuario;
import com.example.API2024.BackEnd.repository.CargoRepository;
import com.example.API2024.BackEnd.repository.CredencialRepository;
import com.example.API2024.BackEnd.repository.UsuarioRepository;

@Service
public class CredencialService {

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	CredencialRepository credencialRepository;
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	public Credencial criarCredencial(String cpf, String senha) throws Exception {
        if (credencialRepository.existsByCpf(cpf)) {
            throw new Exception("CPF em uso");
        }
        Credencial credencial = new Credencial();
        credencial.setCpf(cpf);
        credencial.setSenha(passwordEncoder.encode(senha));
        
        credencialRepository.save(credencial);
        
        return credencial;
    }
	
	public void atualizarCredencial(Usuario usuario, Credencial credencial){
		usuario.setCredencial(credencial);
		usuarioRepository.save(usuario);
		System.out.println("Salvo: " + usuario);
	}
	
	public void alterarSenha(String cpf, String novaSenha) throws Exception {
	    Credencial credencial = credencialRepository.findByCpf(cpf);
	    if (credencial != null) {
	        credencial.setSenha(passwordEncoder.encode(novaSenha));
	        credencialRepository.save(credencial);
	    } else {
	        throw new Exception("Credencial não encontrada para o CPF fornecido");
	    }
	}

}
