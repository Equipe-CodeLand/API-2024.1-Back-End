package com.example.API2024.BackEnd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import com.example.API2024.BackEnd.model.Manutencao;
import com.example.API2024.BackEnd.repository.ManutencaoRepository;

@Service
public class ManutencaoService {

	@Autowired
	private ManutencaoRepository manutencaoRepository;

	public List<Manutencao> findAll() {
		return manutencaoRepository.findAll();
	}

	public Manutencao findById(Long id) {
		return manutencaoRepository.findById(id).get();
	}

    public Manutencao cadastrarManutencao(Manutencao manutencao) {
        return manutencaoRepository.save(manutencao);
    }

}
