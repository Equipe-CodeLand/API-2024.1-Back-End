package com.example.API2024.BackEnd.dto;

import java.sql.Date;

import com.example.API2024.BackEnd.model.Manutencao;

import lombok.Data;

@Data
public class ManutencaoDto {

    private Date dataInicio;

    private Date dataFinal;

    private String localizacao;

    private String responsavel;

    private Long ativos_id;

    public Manutencao toEntity() {
        Manutencao manutencao = new Manutencao();
        manutencao.setDataInicio(dataInicio);
        manutencao.setDataFinal(dataFinal);
        manutencao.setLocalizacao(localizacao);
        manutencao.setResponsavel(responsavel);

        return manutencao;
    }
}
