package com.example.API2024.BackEnd.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Usuario {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@Column
	private String nome;

	@ManyToOne
	@JoinColumn(name = "credencial_id")
	private Credencial credencial;

	@ManyToOne
	@JoinColumn(name = "cargo_id")
	private Cargo cargo;
}
