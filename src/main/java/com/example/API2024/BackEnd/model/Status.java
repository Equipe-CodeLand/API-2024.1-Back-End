package com.example.API2024.BackEnd.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Status {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String nome_status;
}
