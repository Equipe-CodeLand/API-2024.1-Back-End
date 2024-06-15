package com.example.API2024.BackEnd.dto;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.example.API2024.BackEnd.model.Manutencao;

import lombok.Data;

@Data
public class ManutencaoDto {

    private String data_inicio;

    private String data_final;

    private String localizacao;

    private String responsavel;
    
    private String descricao;

    private Long ativos_id;

    public Manutencao toEntity() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        Manutencao manutencao = new Manutencao();
        manutencao.setData_inicio(LocalDate.parse(data_inicio, formatter));
        manutencao.setData_final(LocalDate.parse(data_final, formatter));
        manutencao.setLocalizacao(localizacao);
        manutencao.setResponsavel(responsavel);
        manutencao.setDescricao(descricao);

        return manutencao;
    }
}
