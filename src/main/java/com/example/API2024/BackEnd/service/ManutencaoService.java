package com.example.API2024.BackEnd.service;

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
		Manutencao manutencao = manutencaoRepository.findById(id).get();
		Ativos ativos = repositorio.findById(manutencaoDto.getAtivos_id()).get();

		return manutencaoRepository.save(manutencao.update(manutencaoDto, ativos));
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
}
