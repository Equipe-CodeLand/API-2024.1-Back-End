package com.example.API2024.BackEnd.repository;

import com.example.API2024.BackEnd.model.Ativos;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.API2024.BackEnd.model.Manutencao;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface ManutencaoRepository extends JpaRepository<Manutencao, Long> {
	List<Manutencao> findByAtivos_Nome(String nome);
	
	@Query("SELECT m FROM Manutencao m WHERE m.data_inicio >= :dataInicio")
	List<Manutencao> findByData_inicio(LocalDate dataInicio);
	
	@Query("SELECT m FROM Manutencao m WHERE m.data_final <= :dataFinal")
	List<Manutencao> findByData_final(LocalDate dataFinal);
}