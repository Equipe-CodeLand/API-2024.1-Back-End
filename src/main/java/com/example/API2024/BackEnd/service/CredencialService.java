package com.example.API2024.BackEnd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.API2024.BackEnd.model.Cargo;
import com.example.API2024.BackEnd.model.Credencial;
import com.example.API2024.BackEnd.repository.CargoRepository;
import com.example.API2024.BackEnd.repository.CredencialRepository;

@Service
public class CredencialService {

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	CredencialRepository credencialRepository;
	
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
}
