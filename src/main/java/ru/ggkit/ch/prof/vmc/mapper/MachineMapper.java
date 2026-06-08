package ru.ggkit.ch.prof.vmc.mapper;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.jspecify.annotations.NonNull;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import ru.ggkit.ch.prof.vmc.dto.create.MachineCreateDto;
import ru.ggkit.ch.prof.vmc.dto.read.IncomeReadDto;
import ru.ggkit.ch.prof.vmc.dto.read.InstallationReadDto;
import ru.ggkit.ch.prof.vmc.dto.read.MachineReadDto;
import ru.ggkit.ch.prof.vmc.dto.update.MachineUpdateDto;
import ru.ggkit.ch.prof.vmc.entity.Income;
import ru.ggkit.ch.prof.vmc.entity.Installation;
import ru.ggkit.ch.prof.vmc.entity.Machine;
import ru.ggkit.ch.prof.vmc.entity.PaymentType;

@Mapper(componentModel = "spring")
public interface MachineMapper {

  //create
  @Mapping(target = "paymentTypes", ignore = true)
  @Mapping(target = "inStocks", ignore = true)
  @Mapping(target = "sales", ignore = true)
  Machine createToEntity(MachineCreateDto dto);

  //read
  @Mapping(target = "installation", source = "installations", qualifiedByName = "getInstallation")
  @Mapping(target = "income", source = "installations", qualifiedByName = "getIncome")
  @Mapping(target = "paymentTypes", qualifiedByName = "paymentTypesMap")
  MachineReadDto machineToRead(Machine machine);

  IncomeReadDto incomeToRead(Income income);

  InstallationReadDto installationToRead(Installation installation);

  //update
  @Mapping(target = "installations", ignore = true)
  Machine updateToMachine(MachineUpdateDto dto);

  @Mapping(target = "id", ignore = true)
  void updateMachineFromDto(MachineUpdateDto dto, @MappingTarget Machine machine);


  //default
  @Named("paymentTypesMap")
  default Set<String> paymentTypesMap(@NonNull Set<PaymentType> paymentTypes) {
    return paymentTypes.stream()
        .map(PaymentType::getName)
        .collect(Collectors.toSet());
  }

  @Named("getIncome")
  default IncomeReadDto getIncome(Set<Installation> installations) {
    return activeInstallation(installations)
        .map(Installation::getIncome)
        .map(this::incomeToRead)
        .orElse(null);
  }

  @Named("getInstallation")
  default InstallationReadDto getInstallation(Set<Installation> installations) {
    return activeInstallation(installations)
        .map(this::installationToRead)
        .orElse(null);
  }

  default Optional<Installation> activeInstallation(Set<Installation> installations) {
    if (Objects.isNull(installations)) {
      return Optional.empty();
    }
    return installations.stream()
        .filter(Installation::isActive)
        .findFirst();
  }
}
