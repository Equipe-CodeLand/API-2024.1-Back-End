package com.example.API2024.BackEnd.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.API2024.BackEnd.model.Ativos;
import com.example.API2024.BackEnd.model.dto.AtivosDto;
import com.example.API2024.BackEnd.repository.AtivosRepository;

@RestController
public class AtivosController {

    @Autowired
    public AtivosRepository repositorio;

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
    
    
}
