package com.example.API2024.BackEnd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.API2024.BackEnd.adaptadores.UserDetailsImpl;
import com.example.API2024.BackEnd.model.Credencial;
import com.example.API2024.BackEnd.model.Usuario;
import com.example.API2024.BackEnd.repository.UsuarioRepository;
import com.example.API2024.BackEnd.selecionadores.UsuarioSelecionadorCPF;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	@Autowired
	private UsuarioRepository repositorio;

	@Autowired
	private UsuarioSelecionadorCPF selecionador;

	@Override
	public UserDetails loadUserByUsername(String cpf) throws UsernameNotFoundException {
		List<Usuario> usuarios = repositorio.findAll();
		Usuario usuario = selecionador.selecionar(usuarios, cpf);
		if (usuario == null) {
			throw new UsernameNotFoundException(cpf);
		}
		Credencial credencial = usuario.getCredencial();
		return new UserDetailsImpl(credencial.getCpf(), credencial.getSenha(), usuario.getCargo());
	}
}
