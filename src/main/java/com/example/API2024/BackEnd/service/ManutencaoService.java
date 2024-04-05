package com.example.API2024.BackEnd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import com.example.API2024.BackEnd.model.Manutencao;
import com.example.API2024.BackEnd.model.Ativos;
import com.example.API2024.BackEnd.repository.ManutencaoRepository;
import com.example.API2024.BackEnd.repository.AtivosRepository;

@Service
public class ManutencaoService {

    @Autowired
    private ManutencaoRepository manutencaoRepository;

    @Autowired
    private AtivosRepository ativosRepository;

    public List<Manutencao> findAll() {
        return manutencaoRepository.findAll();
    }

    public Manutencao findById(Long id) {
        return manutencaoRepository.findById(id).get();
    }

    public Manutencao cadastrarManutencao(Manutencao manutencao) {
        if (manutencao.getAtivos() != null && manutencao.getAtivos().getId() != null) {
            Ativos ativos = ativosRepository.findById(manutencao.getAtivos().getId())
                .orElseThrow(() -> new RuntimeException("Ativo não encontrado"));
            manutencao.setAtivos(ativos);
        }
        return manutencaoRepository.save(manutencao);
    }
}