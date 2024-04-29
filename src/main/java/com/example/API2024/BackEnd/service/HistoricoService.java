package com.example.API2024.BackEnd.service;

import com.example.API2024.BackEnd.dto.HistoricoUpdateDto;
import com.example.API2024.BackEnd.model.Ativos;
import com.example.API2024.BackEnd.model.Historico;
import com.example.API2024.BackEnd.repository.HistoricoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HistoricoService {
	@Autowired
	private HistoricoRepository historicoRepository;

	public void addHistorico(Ativos ativo){
		Historico historico = new Historico();
		historico.setAtivo(ativo);
		historico.setData_cadastro(ativo.getDataAquisicao());
		historico.setUsuario(ativo.getUsuario());
        historicoRepository.save(historico);
	}
}
