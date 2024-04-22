package com.example.API2024.BackEnd.dto;

import com.example.API2024.BackEnd.model.Usuario;

import lombok.Data;

@Data
public class UsuarioDto {

    private String nome;

    private Long cargo;

    private String cpf;

    private String senha;

    public Usuario toEntity() {
        Usuario usuario = new Usuario();
        usuario.setNome(nome);

        return usuario;
    }
}
