package com.example.API2024.BackEnd.model;

import com.example.API2024.BackEnd.dto.UsuarioUpdateDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Usuario {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@Column
	private String nome;
	
	@Column(unique = true)
	private String email;

	@ManyToOne
	@JoinColumn(name = "credencial_id")
	private Credencial credencial;
	
	@ManyToOne
	@JoinColumn(name = "cargo_id")
	private Cargo cargo;
		
	@Column
	private boolean estaAtivo;

	public Usuario update(UsuarioUpdateDto usuarioUpdateDto, Cargo cargo) {
		this.setNome(usuarioUpdateDto.getNome());
		this.setCargo(cargo);
		this.credencial.setCpf(usuarioUpdateDto.getCpf());
		return this;
	}
}
