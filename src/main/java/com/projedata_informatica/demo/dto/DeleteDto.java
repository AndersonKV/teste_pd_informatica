package com.projedata_informatica.demo.dto;

import lombok.Data;

@Data
public class DeleteDto {
    private String descricao;

    public DeleteDto(String descricao) {
        this.descricao = descricao;
    }
}
