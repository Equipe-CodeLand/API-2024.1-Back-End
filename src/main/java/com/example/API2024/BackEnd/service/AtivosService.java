package com.example.API2024.BackEnd.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.API2024.BackEnd.dto.AtivosDto;
import com.example.API2024.BackEnd.model.Ativos;
import com.example.API2024.BackEnd.model.Historico;
import com.example.API2024.BackEnd.repository.AtivosRepository;
import com.example.API2024.BackEnd.repository.HistoricoRepository;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Service
public class AtivosService {

	@Autowired
    private AtivosRepository ativosRepository;

	@Autowired
	private HistoricoRepository historicoRepository;

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
		ativo.setCodigo_nota_fiscal(ativos.getCodigo_nota_fiscal());
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
	
    public List<Ativos> findAtivosByUsuarioId(Long usuarioId) {
        return ativosRepository.findByUsuarioId(usuarioId);
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
			List<Historico> historicoList = historicoRepository.findByAtivoId(ativo.getId());
			historicoRepository.deleteAll(historicoList);

			ativosRepository.delete(ativo);
		}
	}
	
	public List<Ativos> filtrarPorDataInicioEDataFinal(LocalDate dataInicio, LocalDate dataFinal) {
		List<Ativos> ativosFiltrados = new ArrayList<Ativos>();
		List<Ativos> ativos = ativosRepository.findAll();
		for(Ativos ativo : ativos) {
			if(ativo.getDataAquisicao().isBefore(dataFinal) || ativo.getDataAquisicao().isEqual(dataFinal)) {
				if(ativo.getDataExpiracao() == null) {					
					ativosFiltrados.add(ativo);
				} else if (ativo.getDataExpiracao().isAfter(dataInicio)){
					ativosFiltrados.add(ativo);
				}
			}
		}
		return ativosFiltrados;
	}
	
	public List<Ativos> filtrarPorExpiracaoProxima(List<Ativos> ativos, LocalDate data, Integer intervalo) {
		List<Ativos> ativosFiltrados = new ArrayList<Ativos>();
		for(Ativos ativo : ativos) {
			if(ativo.getDataExpiracao() != null) {				
				long dias = ChronoUnit.DAYS.between(data, ativo.getDataExpiracao());
				if(dias <= intervalo && dias > 0) {
					ativosFiltrados.add(ativo);
				}
			}
		}
		return ativosFiltrados;
	}
	
	public List<Double> buscarValorTotalPorStatus (List<Ativos> ativos){
		List<Double> valores = new ArrayList<Double>();
		Double valorDisponivel = 0.0;
		Double valorOcupado = 0.0;
		Double valorEmManutencao = 0.0;
		for(Ativos ativo : ativos) {
			switch (ativo.getStatus().getNome_status()) {
			case "Disponível": {
				valorDisponivel += ativo.getPreco_aquisicao();
				break;
			}
			case "Em manutenção": {
				valorEmManutencao += ativo.getPreco_aquisicao();
				break;
			}
			case "Ocupado": {
				valorOcupado += ativo.getPreco_aquisicao();
				break;
			}
			default:
				break;
			}
		}
		valores.add(valorDisponivel);
		valores.add(valorEmManutencao);
		valores.add(valorOcupado);
		return valores;
	}
	
	public List<Integer> buscarQntAtivosPorStatus(List<Ativos> ativos){
		List<Integer> valores = new ArrayList<Integer>();
		Integer valorDisponivel = 0;
		Integer valorOcupado = 0;
		Integer valorEmManutencao = 0;
		
		for(Ativos ativo : ativos) {
			switch (ativo.getStatus().getNome_status()) {
			case "Disponível": {
				valorDisponivel ++;
				break;
			}
			case "Em manutenção": {
				valorEmManutencao ++;
				break;
			}
			case "Ocupado": {
				valorOcupado ++;
				break;
			}
			default:
				break;
			}
		}
		
		valores.add(valorDisponivel);
		valores.add(valorEmManutencao);
		valores.add(valorOcupado);
		return valores;
	}
}
