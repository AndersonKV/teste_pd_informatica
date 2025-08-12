package com.projedata_informatica.demo.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FuncionarioResumoDTO {
    private String nome;
    private int idade;
}