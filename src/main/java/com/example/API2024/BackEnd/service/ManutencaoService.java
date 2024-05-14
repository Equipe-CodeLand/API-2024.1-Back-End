package com.example.API2024.BackEnd.service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.API2024.BackEnd.dto.ManutencaoUpdateDto;
import com.example.API2024.BackEnd.model.Ativos;
import com.example.API2024.BackEnd.model.Manutencao;
import com.example.API2024.BackEnd.repository.AtivosRepository;
import com.example.API2024.BackEnd.repository.ManutencaoRepository;

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
			Ativos ativos = repositorio.findById(manutencaoDto.getAtivos_id()).orElseThrow(() -> new Exception("Ativo não encontrado"));

			return manutencaoRepository.save(manutencao.update(manutencaoDto, ativos));
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
	
}