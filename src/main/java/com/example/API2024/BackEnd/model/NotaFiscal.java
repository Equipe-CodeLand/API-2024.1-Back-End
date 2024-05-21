package com.example.API2024.BackEnd.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
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
	@Column
	private String codigo;
	@Lob
	@Column
	@JsonIgnore
	private byte[] bytes;
	@OneToOne(mappedBy="notaFiscal")
	@JsonIgnore
	private Ativos ativos;
}
