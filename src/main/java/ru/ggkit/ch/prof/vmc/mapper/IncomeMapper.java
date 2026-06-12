package ru.ggkit.ch.prof.vmc.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.ggkit.ch.prof.vmc.dto.create.IncomeCreateDto;
import ru.ggkit.ch.prof.vmc.dto.read.IncomeReadDto;
import ru.ggkit.ch.prof.vmc.dto.update.IncomeUpdateDto;
import ru.ggkit.ch.prof.vmc.entity.Income;

@Mapper(componentModel = "spring")
public interface IncomeMapper {

  @Mapping(target = "installation", ignore = true)
  @Mapping(target = "id", ignore = true)
  Income createDtoToIncome(IncomeCreateDto incomeCreateDto);

  @Mapping(target = "installation", ignore = true)
  @Mapping(target = "id", ignore = true)
  Income updateDtoToIncome(IncomeUpdateDto incomeUpdateDto);

  @Mapping(target = "installationId", source = "income.installation.id")
  IncomeReadDto incomeToReadDto(Income income);
}
