package com.projedata_informatica.demo.dto;

import lombok.Data;

@Data
public class increaseSalaryDto {
    private String descricao;

    public increaseSalaryDto(String descricao) {
        this.descricao = descricao;
    }
}
