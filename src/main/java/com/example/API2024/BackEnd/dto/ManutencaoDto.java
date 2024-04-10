package com.example.API2024.BackEnd.dto;


import java.sql.Date;
import java.time.LocalDate;

import com.example.API2024.BackEnd.model.Manutencao;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class ManutencaoDto {

    private String data_inicio;

    private String data_final;

    private String localizacao;

    private String responsavel;

    private Long ativos_id;

    public Manutencao toEntity() {
        Manutencao manutencao = new Manutencao();
        manutencao.setData_inicio(LocalDate.parse(data_inicio));
        manutencao.setData_final(LocalDate.parse(data_final));
        manutencao.setLocalizacao(localizacao);
        manutencao.setResponsavel(responsavel);

        return manutencao;
    }
}
