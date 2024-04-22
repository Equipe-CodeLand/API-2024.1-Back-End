package com.example.API2024.BackEnd.controller;

import java.util.List;

import com.example.API2024.BackEnd.model.Historico;
import com.example.API2024.BackEnd.model.Usuario;
import com.example.API2024.BackEnd.repository.HistoricoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.API2024.BackEnd.model.Ativos;
import com.example.API2024.BackEnd.dto.AtivosDto;
import com.example.API2024.BackEnd.repository.AtivosRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class AtivosController {

    @Autowired
    public AtivosRepository repositorio;

	@Autowired
	private HistoricoRepository historicoRepository;

    @GetMapping("/listar/ativos")
    public List<Ativos> listarAtivos() {
        return repositorio.findAll();
    }

    @PostMapping("/cadastrar/ativos")
    public void cadastrarAtivos(@RequestBody AtivosDto ativosDto) {
        System.out.println("Recebendo dados do cliente:");
        System.out.println(ativosDto.toString());

        // Convertendo DTO para entidade Ativos
        Ativos ativo = ativosDto.toEntity();

        // Salvando o ativo e obtendo o ativo salvo
        Ativos savedAtivo = repositorio.save(ativo);

        // Verificando o status do ativo
        if ("ocupado".equalsIgnoreCase(savedAtivo.getStatus().getNome_status())) {
            // Salvando no histórico
            Historico historico = new Historico();
            historico.setAtivo(savedAtivo);
            historico.setData_cadastro(savedAtivo.getDataAquisicao());
            historico.setUsuario(savedAtivo.getUsuario());

            historicoRepository.save(historico);
        }
    }

    @PutMapping("/atualizar/ativos/{id}")
    public ResponseEntity<Ativos> atualizarAtivos(@RequestBody AtivosDto ativosDto, @PathVariable Long id) {
        try {
        	Ativos ativo = repositorio.findById(id).orElse(null);
            if (ativo != null) {
                Ativos ativoAtualizado = ativo.update(ativosDto);
                return ResponseEntity.ok(repositorio.save(ativoAtualizado));
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch(Exception e) {
        	System.out.println("Erro ao atualizar a manutenção: " + e.getMessage());
			return null;
        }
    }
    
    @DeleteMapping("/delete/ativos/{id}")
    public void deleteAtivos(@PathVariable Long id) {
        repositorio.deleteById(id);
    }

    @GetMapping("/listar/historico/{id}")
    public List<Historico> listaHistorico(@PathVariable Long id) {
        return historicoRepository.findByAtivoId(id);
    }
}
