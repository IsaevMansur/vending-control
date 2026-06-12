package ru.ggkit.ch.prof.vmc.service;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ggkit.ch.prof.vmc.dto.create.MachineCreateDto;
import ru.ggkit.ch.prof.vmc.dto.read.MachineReadDto;
import ru.ggkit.ch.prof.vmc.dto.update.MachineUpdateDto;
import ru.ggkit.ch.prof.vmc.entity.Machine;
import ru.ggkit.ch.prof.vmc.entity.PaymentType;
import ru.ggkit.ch.prof.vmc.mapper.MachineMapper;
import ru.ggkit.ch.prof.vmc.repository.MachineRepository;

@Service
@RequiredArgsConstructor
public class DefaultMachineService implements MachineService {

  private final MachineRepository repoMachine;
  private final MachineMapper machineMapper;

  @Override
  @Transactional
  public MachineReadDto createMachine(MachineCreateDto machineCreateDto) {
    Machine machine = machineMapper.createDtoToMachine(machineCreateDto);
    Set<PaymentType> paymentTypes = repoMachine.findAllPaymentTypesByIds(
        machineCreateDto.paymentTypes());
    machine.setPaymentTypes(paymentTypes);
    Machine saved = repoMachine.save(machine);
    return machineMapper.machineToReadDto(saved);
  }

  @Override
  @Transactional(readOnly = true)
  public MachineReadDto findMachine(long id) {
    Machine machine = repoMachine.findMachineWithPaymentTypesOrThrow(id);
    return machineMapper.machineToReadDto(machine);
  }

  @Override
  @Transactional
  public void updateMachine(MachineUpdateDto machineUpdateDto) {
    Machine machine = repoMachine.findMachine(machineUpdateDto.id());
    Set<PaymentType> paymentTypes = repoMachine.findAllPaymentTypesByIds(
        machineUpdateDto.paymentTypeIds());
    if (Objects.isNull(machine.getPaymentTypes())
        || machine.getPaymentTypes().size() != paymentTypes.size()) {
      updatePaymentTypes(machine, paymentTypes);
    }
    machineMapper.updateMachineFromDto(machineUpdateDto, machine);
  }

  @Override
  @Transactional
  public void deleteMachine(long id) {
    repoMachine.deleteById(id);
  }

  private void updatePaymentTypes(@NonNull Machine machine,
      @NonNull Set<PaymentType> paymentTypes) {
    if (Objects.isNull(machine.getPaymentTypes())) {
      machine.setPaymentTypes(new HashSet<>(paymentTypes));
      return;
    }
    machine.getPaymentTypes().clear();
    machine.getPaymentTypes().addAll(paymentTypes);
  }
}