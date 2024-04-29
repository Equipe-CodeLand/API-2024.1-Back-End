package com.example.API2024.BackEnd.selecionadores;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.API2024.BackEnd.model.Credencial;
import com.example.API2024.BackEnd.model.Usuario;

@Component
public class UsuarioSelecionadorCPF implements Selecionador<Usuario, String>{

	@Override
	public Usuario selecionar(List<Usuario> objetos, String identificador) {
		Usuario usuario = null;
		for (Usuario objeto : objetos) {
			Credencial credencial = objeto.getCredencial();
			String cpfUsuario = credencial.getCpf();
			if (cpfUsuario.trim().equals(identificador.trim())) {
				usuario = objeto;
				break;
			}
		}
		return usuario;
	}
}
