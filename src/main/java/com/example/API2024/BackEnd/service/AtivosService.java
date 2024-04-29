package com.example.API2024.BackEnd.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.API2024.BackEnd.dto.AtivosDto;
import com.example.API2024.BackEnd.model.Ativos;
import com.example.API2024.BackEnd.repository.AtivosRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.StoredProcedureQuery;

@Service
public class AtivosService {
	
	@Autowired
    private AtivosRepository ativosRepository;

	@Autowired
	private EntityManager entityManager;

	public Ativos update(Long id, AtivosDto ativos) throws Exception{
    	Ativos ativo = ativosRepository.findById(id).orElse(null);
		ativo.setNome(ativos.getNome());
		ativo.setDescricao(ativos.getDescricao());
		ativo.setPreco_aquisicao(ativos.getPreco_aquisicao());
		ativo.setModelo(ativos.getModelo());
		ativo.setMarca(ativos.getMarca());
		ativo.setStatus(ativos.getStatus());
		ativo.setUsuario(ativos.getUsuario());
		ativo.setDataAquisicao(LocalDate.parse(ativos.getDataAquisicao()));
		ativo.setDataExpiracao(LocalDate.parse(ativos.getDataExpiracao()));

		return ativo;
	}
	
	public void atualizarAtivoStatus() {
		StoredProcedureQuery procedimento = entityManager.createStoredProcedureQuery("atualizarStatusAtivo");
		procedimento.execute();
	}
	
}
