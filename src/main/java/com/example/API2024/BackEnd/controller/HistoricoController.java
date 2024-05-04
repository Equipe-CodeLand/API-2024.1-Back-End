package com.example.API2024.BackEnd.controller;

import com.example.API2024.BackEnd.model.Ativos;
import com.example.API2024.BackEnd.model.Historico;
import com.example.API2024.BackEnd.repository.AtivosRepository;
import com.example.API2024.BackEnd.repository.HistoricoRepository;
import com.example.API2024.BackEnd.service.HistoricoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
public class HistoricoController {
	
	@Autowired
	public AtivosRepository ativosRepository;

	@Autowired
	public HistoricoRepository historicoRepository;
	
	@Autowired
	public HistoricoService historicoService;

	@GetMapping("/listar/historico/{id}")
	public List<Historico> listaHistorico(@PathVariable Long id) {
		return historicoRepository.findByAtivoId(id);
	}
	
	@PostMapping("/adicionar/historico/{id}")
    public void adicionarHistorico(@PathVariable Long id) {
		Ativos ativo = ativosRepository.findById(id).orElse(null);
        if (ativo != null) {
            // Cria um novo histórico
            Historico historico = new Historico();
            historico.setAtivo(ativo);
            historico.setData_cadastro(LocalDate.now()); // Use a data atual ou a data do ativo, dependendo dos requisitos
            historico.setUsuario(ativo.getUsuario()); // Supondo que você tenha um usuário associado ao ativo
            historicoRepository.save(historico);
        }
    }
}
