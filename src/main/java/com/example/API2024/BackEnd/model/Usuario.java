package com.example.API2024.BackEnd.model;

import com.example.API2024.BackEnd.Enum.CargoEnum;
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

	@OneToOne
	@JoinColumn(name = "credencial_id")
	private Credencial credencial;

	@Enumerated(EnumType.ORDINAL)
    private CargoEnum cargo;
}
