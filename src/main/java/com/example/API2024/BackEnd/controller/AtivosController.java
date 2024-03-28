package com.example.API2024.BackEnd.controller;

import com.example.API2024.BackEnd.model.Ativos;
import com.example.API2024.BackEnd.repository.AtivosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AtivosController {

    @Autowired
    public AtivosRepository repositorio;

    @GetMapping("/listar/ativos")
    public List<Ativos> listarAtivos() {
        return repositorio.findAll();
    }
}
