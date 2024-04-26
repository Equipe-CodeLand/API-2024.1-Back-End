package com.example.API2024.BackEnd.controller;

import java.util.List;

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
import com.example.API2024.BackEnd.service.AtivosService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class AtivosController {

    @Autowired
    public AtivosRepository repositorio;
    
    @Autowired
    public AtivosService ativoService;

    @GetMapping("/listar/ativos")
    public List<Ativos> listarAtivos() {
        return repositorio.findAll();
    }
    
    @PostMapping("/cadastrar/ativos")
    public void cadastrarAtivos(@RequestBody AtivosDto ativos) {
        System.out.println("Recebendo dados do cliente:");
        System.out.println(ativos.toString());
        repositorio.save(ativos.toEntity());
    }
    
    @PutMapping("/atualizar/ativos/{id}")
    public ResponseEntity<Ativos> atualizarAtivos(@RequestBody AtivosDto ativosDto, @PathVariable Long id) {
        try {
                Ativos ativoAtualizado = ativoService.update(id, ativosDto);
                return ResponseEntity.ok(repositorio.save(ativoAtualizado));
        } catch(Exception e) {
        	System.out.println("Erro ao atualizar a manutenção: " + e.getMessage());
			return null;
        }
    }
    
    @DeleteMapping("/delete/ativos/{id}")
    public void deleteAtivos(@PathVariable Long id) {
        repositorio.deleteById(id);
    }
}
