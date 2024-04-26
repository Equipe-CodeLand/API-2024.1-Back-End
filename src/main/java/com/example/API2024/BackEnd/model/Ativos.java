package com.example.API2024.BackEnd.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import com.example.API2024.BackEnd.dto.AtivosDto;
import com.fasterxml.jackson.annotation.JsonBackReference;

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

	@OneToOne
	@JoinColumn(name = "nota_fiscal_id")
	private NotaFiscal notaFiscal;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    
/*	public Ativos update(AtivosDto ativos){
		this.setNome(ativos.getNome());
		this.setDescricao(ativos.getDescricao());
		this.setPreco_aquisicao(ativos.getPreco_aquisicao());
		this.setModelo(ativos.getModelo());
		this.setMarca(ativos.getMarca());
		this.setStatus(ativos.getStatus());
		this.setUsuario(ativos.getUsuario());
		this.setDataAquisicao(LocalDate.parse(ativos.getDataAquisicao()));
		this.setDataExpiracao(LocalDate.parse(ativos.getDataExpiracao()));
		
		return this;
	}*/

}
