package com.example.API2024.BackEnd.adaptadores;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.API2024.BackEnd.model.Cargo;

public class UserDetailsImpl implements UserDetails{
	private static final long serialVersionUID = 1L;

	private String cpf;
	private String senha;
	private Collection<? extends GrantedAuthority> cargo;
	private boolean estaAtivo;

	public UserDetailsImpl() {
	}

	public UserDetailsImpl(String cpf, String senha, Cargo cargo, boolean estaAtivo) {
		this.cpf = cpf;
		this.senha = senha;
		this.gerarCargo(cargo);
		this.estaAtivo = estaAtivo;
	}
	
	private void gerarCargo(Cargo cargo) {
		List<SimpleGrantedAuthority> autoridadesPerfis = new ArrayList<>();
		autoridadesPerfis.add(new SimpleGrantedAuthority(cargo.getNome()));
		this.cargo = autoridadesPerfis;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.cargo;
	}

	@Override
	public String getPassword() {
		return this.senha;
	}

	@Override
	public String getUsername() {
		return this.cpf;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return estaAtivo;
	}

}
