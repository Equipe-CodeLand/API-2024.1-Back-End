package com.example.API2024.BackEnd.selecionadores;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.API2024.BackEnd.model.Usuario;

@Component
public class UsuarioSelecionadorID implements Selecionador<Usuario, Long>{

	@Override
	public Usuario selecionar(List<Usuario> objetos, Long identificador) {
		Usuario usuario = null;
		for (Usuario objeto : objetos) {
			if (objeto.getId().longValue() == identificador.longValue()) {
				usuario = objeto;
				break;
			}
		}
		return usuario;
	}
}
