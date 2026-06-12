package ru.ggkit.ch.prof.vmc.service;

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
  public MachineReadDto saveMachine(MachineCreateDto machineCreateDto) {
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
    Machine machine = repoMachine.findMachineOrThrow(machineUpdateDto.id());
    Set<PaymentType> paymentTypes = repoMachine.findAllPaymentTypesByIds(
        machineUpdateDto.paymentTypeIds());
    if (Objects.nonNull(machine.getPaymentTypes())) {
      updatePaymentTypes(machine.getPaymentTypes(), paymentTypes);
    }
    machineMapper.updateMachineFromDto(machineUpdateDto, machine);
  }

  @Override
  @Transactional
  public void archiveMachine(long id) {
    repoMachine.archiveMachine(id);
  }

  private void updatePaymentTypes(
      @NonNull Set<PaymentType> oldest,
      @NonNull Set<PaymentType> newest) {
    oldest.clear();
    oldest.addAll(newest);
  }
}