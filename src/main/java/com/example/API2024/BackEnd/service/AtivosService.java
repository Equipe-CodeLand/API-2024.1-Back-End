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
		String atualizarStatusDisponivel = "UPDATE ativos JOIN manutencao ON ativos.id = manutencao.ativos_id SET ativos.status_id = 1 WHERE CURDATE() NOT BETWEEN manutencao.data_inicio AND manutencao.data_final AND ativos.id = 3";
		String atualizarStatusManutencao = "UPDATE ativos JOIN manutencao ON ativos.id = manutencao.ativos_id SET ativos.status_id = 2 WHERE CURRENT_DATE() BETWEEN manutencao.data_inicio AND manutencao.data_final";
		entityManager.createNativeQuery(atualizarStatusDisponivel).executeUpdate();
		entityManager.createNativeQuery(atualizarStatusManutencao).executeUpdate();
	}
}
