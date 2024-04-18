package com.example.API2024.BackEnd.controller;

import com.example.API2024.BackEnd.model.Usuario;
import com.example.API2024.BackEnd.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UsuariosController {

    @Autowired
    public UsuarioRepository repositorio;

    @GetMapping("/listar/usuarios")
    public List<Usuario> listarUsuarios() { return repositorio.findAll(); }

}
