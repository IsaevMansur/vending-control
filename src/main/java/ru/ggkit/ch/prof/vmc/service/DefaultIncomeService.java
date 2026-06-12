package ru.ggkit.ch.prof.vmc.service;

import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ggkit.ch.prof.vmc.dto.create.IncomeCreateDto;
import ru.ggkit.ch.prof.vmc.dto.read.IncomeReadDto;
import ru.ggkit.ch.prof.vmc.entity.Income;
import ru.ggkit.ch.prof.vmc.entity.Installation;
import ru.ggkit.ch.prof.vmc.mapper.IncomeMapper;
import ru.ggkit.ch.prof.vmc.repository.IncomeRepository;
import ru.ggkit.ch.prof.vmc.repository.InstallationRepository;

@Service
@RequiredArgsConstructor
public class DefaultIncomeService implements IncomeService {

  private final IncomeRepository repoIncome;
  private final InstallationRepository repoInstallation;
  private final IncomeMapper incomeMapper;

  @Override
  @Transactional
  public IncomeReadDto saveIncome(IncomeCreateDto incomeCreateDto) {
    Installation installation = repoInstallation
        .findInstallation(incomeCreateDto.installationId());
    Income income = incomeMapper.createDtoToIncome(incomeCreateDto);
    income.setInstallation(installation);
    Income saved = repoIncome.save(income);
    return incomeMapper.incomeToReadDto(saved);
  }

  @Override
  @Transactional(readOnly = true)
  public IncomeReadDto findIncome(long id) {
    Income found = repoIncome.findIncome(id);
    return incomeMapper.incomeToReadDto(found);
  }

  @Override
  @Transactional(readOnly = true)
  public Set<IncomeReadDto> findIncomesForMachine(long machineId) {
    Set<Income> founds = repoIncome.findAllIncomeByMachineId(machineId);
    return founds.stream()
        .map(incomeMapper::incomeToReadDto)
        .collect(Collectors.toSet());
  }
}