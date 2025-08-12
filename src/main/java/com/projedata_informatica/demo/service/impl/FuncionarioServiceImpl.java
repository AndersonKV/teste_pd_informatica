package com.projedata_informatica.demo.service.impl;


import com.projedata_informatica.demo.dto.*;
import com.projedata_informatica.demo.model.Funcionario;
import com.projedata_informatica.demo.service.FuncionarioService;
import com.projedata_informatica.demo.util.FormatadorUtil;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class FuncionarioServiceImpl implements FuncionarioService {
    private List<Funcionario> funcionarios = new ArrayList<>();

    @PostConstruct
    public void init() {
        funcionarios = new ArrayList<>(Arrays.asList(
                new Funcionario("Maria", LocalDate.of(2000, 10, 18), BigDecimal.valueOf(2009.44), "Operador"),
                new Funcionario("João", LocalDate.of(1990, 5, 12), BigDecimal.valueOf(2284.38), "Operador"),
                new Funcionario("Caio", LocalDate.of(1961, 5, 2), BigDecimal.valueOf(9836.14), "Coordenador"),
                new Funcionario("Miguel", LocalDate.of(1988, 10, 14), BigDecimal.valueOf(19119.88), "Diretor"),
                new Funcionario("Alice", LocalDate.of(1995, 1, 5), BigDecimal.valueOf(2234.68), "Recepcionista"),
                new Funcionario("Heitor", LocalDate.of(1999, 11, 19), BigDecimal.valueOf(1582.72), "Operador"),
                new Funcionario("Arthur", LocalDate.of(1993, 3, 31), BigDecimal.valueOf(4071.84), "Contador"),
                new Funcionario("Laura", LocalDate.of(1994, 7, 8), BigDecimal.valueOf(3017.45), "Gerente"),
                new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), BigDecimal.valueOf(1606.85), "Eletricista"),
                new Funcionario("Helena", LocalDate.of(1996, 9, 2), BigDecimal.valueOf(2799.93), "Gerente")
        ));
    }

    public List<FuncionarioDTO> list() {
        return funcionarios.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public DeleteDto delete(String nome) {
        Boolean hasDelete = funcionarios.removeIf(f -> f.getNome().equalsIgnoreCase(nome));

        if (!hasDelete) {
            return new DeleteDto("Funcionário não encontrado.");
        }
        return new DeleteDto("Funcionário deletado.");

    }

    public increaseSalaryDto increaseSalary() {
        if (funcionarios.size() == 0) {
            return new increaseSalaryDto("Não há funcionarios.");
        }
        funcionarios.forEach(f ->
                f.setSalario(f.getSalario().multiply(BigDecimal.valueOf(1.10)))
        );

        return new increaseSalaryDto("Salário dos funcionários aumentado.");
    }

    public Map<String, List<FuncionarioDTO>> byFunction() {
        return funcionarios.stream()
                .collect(Collectors.groupingBy(Funcionario::getFuncao,
                        Collectors.mapping(this::toDTO, Collectors.toList())));
    }

    public List<FuncionarioDTO> birthdayPeople(int... meses) {
        Set<Integer> mesesSet = Arrays.stream(meses).boxed().collect(Collectors.toSet());
        return funcionarios.stream()
                .filter(f -> mesesSet.contains(f.getDataNascimento().getMonthValue()))
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public FuncionarioResumoDTO olderEmployee() {
        Funcionario maisVelho = Collections.min(funcionarios, Comparator.comparing(Funcionario::getDataNascimento));
        int idade = LocalDate.now().getYear() - maisVelho.getDataNascimento().getYear();
        return new FuncionarioResumoDTO(maisVelho.getNome(), idade);
    }

    public List<FuncionarioDTO> sortedAlphabetically() {
        return funcionarios.stream()
                .sorted(Comparator.comparing(Funcionario::getNome))
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public TotalSalaryDto totalSalary() {
        BigDecimal total = funcionarios.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        TotalSalaryDto dto = new TotalSalaryDto();
        dto.setDescricao("Salário total dos funcionários: " + FormatadorUtil.formatarMoeda(total));
        return dto;
    }

    public Map<String, String> minimumSalary() {
        BigDecimal salarioMinimo = BigDecimal.valueOf(1212.00);
        return funcionarios.stream()
                .collect(Collectors.toMap(
                        Funcionario::getNome,
                        f -> f.getSalario().divide(salarioMinimo, 2, BigDecimal.ROUND_HALF_UP).toString()
                ));
    }

    private FuncionarioDTO toDTO(Funcionario f) {
        FuncionarioDTO dto = new FuncionarioDTO();
        dto.setNome(f.getNome());
        dto.setDataNascimento(FormatadorUtil.formatarData(f.getDataNascimento()));
        dto.setSalario("R$ " + FormatadorUtil.formatarMoeda(f.getSalario()));
        dto.setFuncao(f.getFuncao());
        return dto;
    }
}
