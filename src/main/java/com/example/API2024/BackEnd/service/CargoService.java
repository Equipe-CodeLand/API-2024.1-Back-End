package com.example.API2024.BackEnd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.API2024.BackEnd.model.Cargo;
import com.example.API2024.BackEnd.repository.CargoRepository;

@Service
public class CargoService {

	@Autowired
	CargoRepository cargoRepository;
	
	public Cargo buscarCargoPorId(Long id) throws Exception {
		Cargo cargo = cargoRepository.findById(id).orElseThrow(() -> new Exception("Cargo não encontrado"));
		return cargo;
	}
}
