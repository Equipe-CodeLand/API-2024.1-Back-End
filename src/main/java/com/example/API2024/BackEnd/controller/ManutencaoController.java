package com.example.API2024.BackEnd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.API2024.BackEnd.model.Manutencao;
import com.example.API2024.BackEnd.service.ManutencaoService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class ManutencaoController {

    @Autowired
    private ManutencaoService manutencaoService;

    @PostMapping("/manutencaoCadastro")
    public Manutencao createManutencao(@RequestBody Manutencao manutencao) {
        return manutencaoService.cadastrarManutencao(manutencao);
    }
}