package com.example.API2024.BackEnd.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Cargo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@Column
	private String nome;
}
