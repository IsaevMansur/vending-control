package ru.ggkit.ch.prof.vmc.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.ggkit.ch.prof.vmc.dto.create.IncomeCreateDto;
import ru.ggkit.ch.prof.vmc.dto.read.IncomeReadDto;
import ru.ggkit.ch.prof.vmc.dto.update.IncomeUpdateDto;
import ru.ggkit.ch.prof.vmc.entity.Income;

@Mapper(componentModel = "spring")
public interface IncomeMapper {

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "machine", ignore = true)
  Income createDtoToIncome(IncomeCreateDto incomeCreateDto);

  @Mapping(target = "machine", ignore = true)
  @Mapping(target = "id", ignore = true)
  Income updateDtoToIncome(IncomeUpdateDto incomeUpdateDto);

  IncomeReadDto incomeToReadDto(Income income);
}
