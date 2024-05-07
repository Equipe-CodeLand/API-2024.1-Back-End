package com.example.API2024.BackEnd.repository;

import com.example.API2024.BackEnd.model.Credencial;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CredencialRepository extends JpaRepository<Credencial, Long> {
	boolean existsByCpf(String cpf);
	
	Credencial findByCpf(String cpf);
}
