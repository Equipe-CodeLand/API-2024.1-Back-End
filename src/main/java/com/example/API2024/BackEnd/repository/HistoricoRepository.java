package com.example.API2024.BackEnd.repository;

import com.example.API2024.BackEnd.model.Historico;
import com.example.API2024.BackEnd.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HistoricoRepository extends JpaRepository<Historico, Long> {
	List<Historico> findByAtivoId(Long id);
}
