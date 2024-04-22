package com.example.API2024.BackEnd.selecionadores;

import java.util.List;

public interface Selecionador<T,ID>{
	public T selecionar(List<T> objetos, ID identificador);
}
