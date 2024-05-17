package com.example.API2024.BackEnd.repository;

import com.example.API2024.BackEnd.model.Ativos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AtivosRepository extends JpaRepository<Ativos, Long> {
	Ativos findByNome(String nome);
}
