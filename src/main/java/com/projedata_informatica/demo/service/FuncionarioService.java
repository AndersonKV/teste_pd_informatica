package com.projedata_informatica.demo.service;

import com.projedata_informatica.demo.dto.*;

import java.util.*;

public interface FuncionarioService {
    DeleteDto delete(String nome);

    increaseSalaryDto increaseSalary();
    List<FuncionarioDTO> list();

    Map<String, List<FuncionarioDTO>> byFunction();

    List<FuncionarioDTO> birthdayPeople(int... meses);

    FuncionarioResumoDTO olderEmployee();

    List<FuncionarioDTO> sortedAlphabetically();

    TotalSalaryDto totalSalary();

    Map<String, String> minimumSalary();

}
