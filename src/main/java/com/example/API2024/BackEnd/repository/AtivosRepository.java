package com.example.API2024.BackEnd.repository;

import com.example.API2024.BackEnd.model.Ativos;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AtivosRepository extends JpaRepository<Ativos, Long> {
    List<Ativos> findByUsuarioId(Long usuarioId);
}
