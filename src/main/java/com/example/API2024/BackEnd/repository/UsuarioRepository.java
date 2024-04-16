package com.example.API2024.BackEnd.repository;

import com.example.API2024.BackEnd.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
