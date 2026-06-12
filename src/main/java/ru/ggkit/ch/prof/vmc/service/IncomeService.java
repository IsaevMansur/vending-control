package ru.ggkit.ch.prof.vmc.service;

import java.util.Set;
import ru.ggkit.ch.prof.vmc.dto.create.IncomeCreateDto;
import ru.ggkit.ch.prof.vmc.dto.read.IncomeReadDto;

public interface IncomeService {

  IncomeReadDto saveIncome(IncomeCreateDto incomeCreateDto);

  IncomeReadDto findIncome(long id);

  Set<IncomeReadDto> findIncomesForMachine(long machineId);
}
