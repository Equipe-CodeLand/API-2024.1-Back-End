package com.example.API2024.BackEnd.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.example.API2024.BackEnd.dto.ManutencaoUpdateDto;
import com.example.API2024.BackEnd.dto.UsuarioUpdateDto;

@Data
@Entity
public class Usuario {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@Column
	private String nome;
	
	@Column
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
