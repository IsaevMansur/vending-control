package ru.ggkit.ch.prof.vmc.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.ggkit.ch.prof.vmc.dto.create.MachineCreateDto;
import ru.ggkit.ch.prof.vmc.dto.read.MachineReadDto;
import ru.ggkit.ch.prof.vmc.dto.update.MachineUpdateDto;
import ru.ggkit.ch.prof.vmc.entity.Machine;

@Mapper(
    componentModel = "spring",
    uses = {
        InstallationMapper.class,
        IncomeMapper.class,
        PaymentTypeMapper.class
    }
)
public interface MachineMapper {


  @Mapping(target = "archived", ignore = true)
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "paymentTypes", ignore = true)
  Machine createDtoToMachine(MachineCreateDto machineCreateDto);

  MachineReadDto machineToReadDto(Machine machine);

  @Mapping(target = "paymentTypes", ignore = true)
  Machine updateDtoToMachine(MachineUpdateDto machineUpdateDto);

  @Mapping(target = "paymentTypes", ignore = true)
  void updateMachineFromDto(MachineUpdateDto machineUpdateDto, @MappingTarget Machine machine);
}