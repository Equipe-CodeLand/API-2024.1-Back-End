package com.example.API2024.BackEnd.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Ativos {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String nome;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "nota_fiscal_id", referencedColumnName = "id")
	private NotaFiscal notaFiscal;
	
	@Column
	private String descricao;

	@Column
	private double preco_aquisicao;

	@Column
	private String modelo;

	@Column
	private String marca;
	
	@Column
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private LocalDate dataAquisicao;

	@Column
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private LocalDate dataExpiracao;

	@OneToMany(mappedBy = "ativos", cascade = CascadeType.REMOVE)
	@JsonBackReference
	private List<Manutencao> manutencao = new ArrayList<>();

	@ManyToOne
	@JoinColumn(name="status_id")
	private Status status;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

}