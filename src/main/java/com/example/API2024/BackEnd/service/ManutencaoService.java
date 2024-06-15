package com.example.API2024.BackEnd.service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import com.example.API2024.BackEnd.dto.ManutencaoUpdateDto;
import com.example.API2024.BackEnd.model.Ativos;
import com.example.API2024.BackEnd.model.Manutencao;
import com.example.API2024.BackEnd.repository.AtivosRepository;
import com.example.API2024.BackEnd.repository.ManutencaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManutencaoService {
	
	@Autowired
	private ManutencaoRepository manutencaoRepository;
	
	@Autowired
	private AtivosRepository ativosRepository;
	
	@Autowired
	private AtivosRepository repositorio;
	
	public Manutencao updateManutencao(ManutencaoUpdateDto manutencaoDto, Long id){
		try {
			Manutencao manutencao = manutencaoRepository.findById(id).orElseThrow(() -> new Exception("Manutenção não encontrada"));
			Ativos ativo = repositorio.findById(manutencaoDto.getAtivos_id()).orElseThrow(() -> new Exception("Ativo não encontrado"));
			return manutencaoRepository.save(manutencao.update(manutencaoDto, ativo));
		} catch (Exception e) {
			System.out.println("Erro ao atualizar a manutenção: " + e.getMessage());
			return null;
		}
	}
	
	public List<Manutencao> findAll() {
		return manutencaoRepository.findAll();
	}
	
	public Manutencao findById(Long id) {
		return manutencaoRepository.findById(id).get();
	}
	
	public Manutencao cadastrarManutencao(Manutencao manutencao, Long id) {
		Ativos ativos = ativosRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Ativo não encontrado"));
		ativos.setUsuario(null);
		manutencao.setAtivos(ativos);
		return manutencaoRepository.save(manutencao);
	}
	
	public Manutencao filtrarPorId(Long id) throws Exception {
		return manutencaoRepository.findById(id).orElseThrow(() -> new Exception("Manutenção não encontrada"));
	}
	
	public List<Manutencao> filtrarPorNomeAtivo(String nomeAtivo) {
		try {
			String nomeAtivoDecodificado = URLDecoder.decode(nomeAtivo, "UTF-8");
			return manutencaoRepository.findByAtivos_Nome(nomeAtivoDecodificado);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}
	
	public List<Manutencao> filtrarPorDataInicio(LocalDate dataInicio) {
		return manutencaoRepository.findByData_inicio(dataInicio);
	}
	
	public List<Manutencao> filtrarPorDataFinal(LocalDate dataFinal) {
		return manutencaoRepository.findByData_final(dataFinal);
	}
	
	public List<Manutencao> filtrarPorDataInicioEDataFinal(LocalDate dataInicio, LocalDate dataFinal) {
		return manutencaoRepository.findByDataInicioAndDataFinal(dataInicio, dataFinal);
	}
	
	public List<Integer> buscarQntManutencoesProximos (List<Manutencao> manutencoes, LocalDate data){
		List<Integer> valores = new ArrayList<Integer>();
		Integer periodo1 = 0;
		Integer periodo2 = 0;
		Integer periodo3 = 0;
		
		for( Manutencao manutencao : manutencoes) {
			long prazo = ChronoUnit.DAYS.between(data, manutencao.getData_inicio());
			if (prazo > 0 && prazo <= 15) {
				periodo1 ++;
			}
			else if (prazo > 0 && prazo <= 30) {
				periodo2 ++;
			}
			else if (prazo > 0 && prazo <= 60) {
				periodo3 ++;
			}
		}
		
		valores.add(periodo1);
		valores.add(periodo2);
		valores.add(periodo3);
		return valores;
	}
	
}