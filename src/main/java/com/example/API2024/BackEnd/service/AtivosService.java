package com.example.API2024.BackEnd.service;

import java.time.LocalDate;
import java.util.List;

import com.example.API2024.BackEnd.model.Historico;
import com.example.API2024.BackEnd.repository.HistoricoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.API2024.BackEnd.dto.AtivosDto;
import com.example.API2024.BackEnd.dto.ManutencaoUpdateDto;
import com.example.API2024.BackEnd.model.Ativos;
import com.example.API2024.BackEnd.model.Manutencao;
import com.example.API2024.BackEnd.repository.AtivosRepository;
import com.example.API2024.BackEnd.repository.ManutencaoRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.StoredProcedureQuery;

@Service
public class AtivosService {

	@Autowired
    private AtivosRepository ativosRepository;

	@Autowired
	private HistoricoRepository historicoRepository;

	@Autowired
	private HistoricoService historicoService;

	@Autowired
	private EntityManager entityManager;

	public Ativos update(Long id, AtivosDto ativos) throws Exception{
    	Ativos ativo = ativosRepository.findById(id).orElse(null);
		ativo.setNome(ativos.getNome());
		ativo.setNotaFiscal(ativos.getNotaFiscal());
		ativo.setDescricao(ativos.getDescricao());
		ativo.setPreco_aquisicao(ativos.getPreco_aquisicao());
		ativo.setModelo(ativos.getModelo());
		ativo.setMarca(ativos.getMarca());
		ativo.setStatus(ativos.getStatus());
		ativo.setDataAquisicao(LocalDate.parse(ativos.getDataAquisicao()));

		if (ativos.getDataExpiracao() == null) {
			ativo.setDataExpiracao(null);
		} else {
			ativo.setDataExpiracao(LocalDate.parse(ativos.getDataExpiracao()));
		}

		if (ativos.getUsuario() == null) {
			ativo.setUsuario(null);
		} else {
			ativo.setUsuario(ativos.getUsuario());
		}

		return ativo;
	}

	@Transactional
	public void atualizarAtivoStatus() {
		String atualizarStatusDisponivel = "UPDATE ativos SET status_id = 1 WHERE status_id = 2";
		String atualizarStatusManutencao = "UPDATE ativos JOIN manutencao ON ativos.id = manutencao.ativos_id SET ativos.status_id = 2 WHERE CURRENT_DATE() BETWEEN manutencao.data_inicio AND manutencao.data_final";
		entityManager.createNativeQuery(atualizarStatusDisponivel).executeUpdate();
		entityManager.createNativeQuery(atualizarStatusManutencao).executeUpdate();
	}

	@Transactional
	public void delete(Long id) {
		Ativos ativo = ativosRepository.findById(id).orElse(null);
		if (ativo != null) {
			// Excluir o histórico associado ao ativo
			List<Historico> historicoList = historicoRepository.findByAtivoId(ativo.getId());
			historicoRepository.deleteAll(historicoList);

			// Excluir o ativo
			ativosRepository.delete(ativo);
		}
	}
}
