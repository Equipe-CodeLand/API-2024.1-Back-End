package com.example.API2024.BackEnd.repository;

import com.example.API2024.BackEnd.model.Ativos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface AtivosRepository extends JpaRepository<Ativos, Long> {
	Ativos findByNome(String nome);
	
    List<Ativos> findByUsuarioId(Long usuarioId);
}
