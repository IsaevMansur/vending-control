package ru.ggkit.ch.prof.vmc.service;

import jakarta.persistence.EntityNotFoundException;
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
public class MachineService {

  private final MachineRepository repo;

  private final MachineMapper mapper;

  @Transactional
  public Long createMachine(MachineCreateDto dto) {
    Machine toSave = mapper.createToEntity(dto);
    Set<PaymentType> paymentTypes = repo.findAllPaymentTypesByIds(dto.paymentTypes());
    toSave.setPaymentTypes(paymentTypes);
    Machine save = repo.save(toSave);
    return save.getId();
  }

  @Transactional(readOnly = true)
  public MachineReadDto findMachine(long id) {
    Machine machine = repo.findMachineWithPaymentTypes(id)
        .orElseThrow(EntityNotFoundException::new);
    return mapper.machineToRead(machine);
  }

  @Transactional
  public void updateMachine(@NonNull MachineUpdateDto dto) {
    Machine machine = repo.findById(dto.id())
        .orElseThrow(EntityNotFoundException::new);
    Set<PaymentType> paymentTypes = repo.findAllPaymentTypesByIds(dto.paymentTypeIds());
    if (machine.getPaymentTypes().size() != paymentTypes.size()) {
      addPaymentTypes(machine, paymentTypes);
    }
    mapper.updateMachineFromDto(dto, machine);
  }

  @Transactional
  public void deleteMachine(long id) {
    repo.deleteById(id);
  }

  private void addPaymentTypes(@NonNull Machine machine, @NonNull Set<PaymentType> paymentTypes) {
    machine.getPaymentTypes().clear();
    machine.getPaymentTypes().addAll(paymentTypes);
  }
}