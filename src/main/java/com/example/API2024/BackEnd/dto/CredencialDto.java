package com.example.API2024.BackEnd.dto;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.API2024.BackEnd.model.Credencial;

import lombok.Data;

@Data
public class CredencialDto {
		private final BCryptPasswordEncoder codificador = new BCryptPasswordEncoder();

	    private String cpf;

	    private String senha;

	    public Credencial toEntity() {
	        Credencial credencial = new Credencial();
	        credencial.setCpf(cpf);
	        credencial.setSenha(codificador.encode(senha));
	        return credencial;
	    }
}
