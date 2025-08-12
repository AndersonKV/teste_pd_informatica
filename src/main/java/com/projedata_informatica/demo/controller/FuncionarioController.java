package com.projedata_informatica.demo.controller;


import com.projedata_informatica.demo.dto.*;
import com.projedata_informatica.demo.service.FuncionarioService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/funcionarios")
@RequiredArgsConstructor
public class FuncionarioController {

    private final FuncionarioService service;

    @GetMapping
    @Operation(summary = "Lista todos os funcionários")
    public List<FuncionarioDTO> list() {
        return service.list();
    }

    @DeleteMapping("/remover")
    @Operation(summary = "Remove o funcionário")
    public DeleteDto delete(@RequestParam String nome) {
        return service.delete(nome);
    }

    @PostMapping("/aumentar-salario")
    @Operation(summary = "Aumenta salário de todos em 10%")
    public increaseSalaryDto increaseSalary() {
        return service.increaseSalary();
    }

    @GetMapping("/por-funcao")
    @Operation(summary = "Agrupa funcionários por função")
    public Map<String, List<FuncionarioDTO>> byFunction() {
        return service.byFunction();
    }

    @GetMapping("/aniversariantes")
    @Operation(summary = "Lista aniversariantes dos meses informados")
    public List<FuncionarioDTO> birthdayPeople(@RequestParam List<Integer> meses) {
        return service.birthdayPeople(meses.stream().mapToInt(i -> i).toArray());
    }

    @GetMapping("/mais-velho")
    @Operation(summary = "Mostra funcionário mais velho")
    public FuncionarioResumoDTO olderEmployee() {
        return service.olderEmployee();
    }

    @GetMapping("/ordenados")
    @Operation(summary = "Lista funcionários em ordem alfabética")
    public List<FuncionarioDTO> sortedAlphabetically() {
        return service.sortedAlphabetically();
    }

    @GetMapping(value = "/total-salarios", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Mostra o total dos salários")
    public TotalSalaryDto totalSalary() {
        return service.totalSalary();
    }

    @GetMapping("/salarios-minimos")
    @Operation(summary = "Calcula quantos salários mínimos cada funcionário recebe")
    public Map<String, String> minimumSalary() {
        return service.minimumSalary();
    }
}
