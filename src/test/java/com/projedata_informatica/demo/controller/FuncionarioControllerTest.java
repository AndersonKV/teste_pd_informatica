package com.projedata_informatica.demo.controller;

import com.projedata_informatica.demo.model.Funcionario;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class FuncionarioControllerTest {
    @Autowired
    private FuncionarioController funcionarioController;

    @Test
    void shouldIncreaseSalary() {
        var result = funcionarioController.increaseSalary();
        Assert.assertEquals(result.getDescricao(), "Salário dos funcionários aumentado.");
    }

    @Test
    void notShouldIncreaseSalary() {
        deleteAllEmployed();
        var result = funcionarioController.increaseSalary();
        Assert.assertEquals(result.getDescricao(), "Não há funcionarios.");
    }

    @Test
    void shouldListEmployed() {
        var result = funcionarioController.list();
        Assert.assertEquals(result.size(), 10);
    }

    @Test
    void shouldListEmptyEmployed() {
        deleteAllEmployed();
        var result = funcionarioController.list();
        Assert.assertEquals(result.size(), 0);
    }


    @Test
    void shouldReturnTotalSalary() {
        var result = funcionarioController.totalSalary();
        Assert.assertEquals(result.getDescricao(), "Salário total dos funcionários: 48.563,31");
    }

    @Test
    void shouldReturnMinimumSalary() {
        List<Funcionario> funcionarios = new ArrayList<>(Arrays.asList(
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

        var result = funcionarioController.minimumSalary();

        Assert.assertEquals(result.size(), funcionarios.size());



    }

    @Test
    void shouldReturnByFunction() {
        var result = funcionarioController.byFunction();

        Assert.assertEquals(result.get("Operador").size(), 3);
        Assert.assertEquals(result.get("Eletricista").size(),1);
        Assert.assertEquals(result.get("Recepcionista").size(), 1);
        Assert.assertEquals(result.get("Diretor").size(), 1);
        Assert.assertEquals(result.get("Gerente").size(), 2);
        Assert.assertEquals(result.get("Coordenador").size(), 1);
        Assert.assertEquals(result.get("Contador").size(), 1);

    }



    @Test
    void shouldReturnOlderEmployee() {
        var result = funcionarioController.olderEmployee();
        Assert.assertEquals(result.getNome(), "Caio");
        Assert.assertEquals(result.getIdade(), 64);
    }



    @Test
    void shouldReturnSortedAlphabetically() {
        var result = funcionarioController.sortedAlphabetically();
        Assert.assertEquals(result.get(0).getNome(), "Alice");
        Assert.assertEquals(result.get(1).getNome(), "Arthur");
        Assert.assertEquals(result.get(2).getNome(), "Caio");
        Assert.assertEquals(result.get(3).getNome(), "Heitor");
        Assert.assertEquals(result.get(4).getNome(), "Helena");
        Assert.assertEquals(result.get(5).getNome(), "Heloísa");
        Assert.assertEquals(result.get(6).getNome(), "João");
        Assert.assertEquals(result.get(7).getNome(), "Laura");
        Assert.assertEquals(result.get(8).getNome(), "Maria");
        Assert.assertEquals(result.get(9).getNome(), "Miguel");
    }

    @Test
    void shouldReturnBirthdayPeople() {
        var result = funcionarioController.birthdayPeople(List.of(1,7));

        Assert.assertEquals("Alice", result.get(0).getNome());
        Assert.assertEquals("05/01/1995", result.get(0).getDataNascimento());
        Assert.assertEquals("R$ 2.234,68", result.get(0).getSalario());
        Assert.assertEquals("Recepcionista", result.get(0).getFuncao());

        Assert.assertEquals("Laura", result.get(1).getNome());
        Assert.assertEquals("08/07/1994", result.get(1).getDataNascimento());
        Assert.assertEquals("R$ 3.017,45", result.get(1).getSalario());
        Assert.assertEquals("Gerente", result.get(1).getFuncao());


    }


    @Test
    void shouldDeleteEmployee() {
        var result = funcionarioController.delete("maria");
        Assert.assertEquals(result.getDescricao(), "Funcionário deletado.");
     }

    @Test
    void shouldNotDeleteEmployee() {
        funcionarioController.delete("maria");
        var result = funcionarioController.delete("maria");
        Assert.assertEquals(result.getDescricao(), "Funcionário não encontrado.");
    }

    void deleteAllEmployed() {
        funcionarioController.delete("Maria");
        funcionarioController.delete("João");
        funcionarioController.delete("Caio");
        funcionarioController.delete("Miguel");
        funcionarioController.delete("Alice");
        funcionarioController.delete("Heitor");
        funcionarioController.delete("Arthur");
        funcionarioController.delete("Laura");
        funcionarioController.delete("Heloísa");
        funcionarioController.delete("Helena");
    }

}
