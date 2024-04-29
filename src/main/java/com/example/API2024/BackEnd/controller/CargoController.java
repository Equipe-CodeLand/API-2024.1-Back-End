package com.example.API2024.BackEnd.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.API2024.BackEnd.model.Cargo;
import com.example.API2024.BackEnd.service.CargoService;

import java.util.List;

@RestController
@RequestMapping("/api/cargos")
public class CargoController {

    @Autowired
    private CargoService cargoService;

    @GetMapping
    public ResponseEntity<List<Cargo>> listarCargos() {
        List<Cargo> cargos = cargoService.listarTodosCargos();
        return ResponseEntity.ok(cargos);
    }
}
