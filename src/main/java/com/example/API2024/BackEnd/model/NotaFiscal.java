package com.example.API2024.BackEnd.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class NotaFiscal {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	@Column
	private String nome;
	@Column
	private String tamanho;
	@Column
	private String tipo;
	@Lob
	@Column
	private byte[] bytes;
	@OneToOne(mappedBy="notaFiscal")
	@JsonManagedReference
	private Ativos ativos;
}
