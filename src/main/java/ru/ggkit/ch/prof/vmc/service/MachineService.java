package ru.ggkit.ch.prof.vmc.service;

import jakarta.persistence.EntityNotFoundException;
import java.util.HashSet;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ggkit.ch.prof.vmc.dto.create.InstallationCreateDto;
import ru.ggkit.ch.prof.vmc.dto.create.MachineCreateDto;
import ru.ggkit.ch.prof.vmc.dto.create.MaintenanceCreateDto;
import ru.ggkit.ch.prof.vmc.dto.create.PaymentTypeCreateDto;
import ru.ggkit.ch.prof.vmc.dto.read.MachineReadDto;
import ru.ggkit.ch.prof.vmc.dto.update.MachineUpdateDto;
import ru.ggkit.ch.prof.vmc.entity.Income;
import ru.ggkit.ch.prof.vmc.entity.Installation;
import ru.ggkit.ch.prof.vmc.entity.Machine;
import ru.ggkit.ch.prof.vmc.entity.Maintenance;
import ru.ggkit.ch.prof.vmc.entity.PaymentType;
import ru.ggkit.ch.prof.vmc.entity.User;
import ru.ggkit.ch.prof.vmc.mapper.MachineMapper;
import ru.ggkit.ch.prof.vmc.repository.IncomeRepository;
import ru.ggkit.ch.prof.vmc.repository.InstallationRepository;
import ru.ggkit.ch.prof.vmc.repository.MachineRepository;
import ru.ggkit.ch.prof.vmc.repository.MaintenanceRepository;
import ru.ggkit.ch.prof.vmc.repository.PaymentTypeRepository;
import ru.ggkit.ch.prof.vmc.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class MachineService {

  private final MachineRepository machineRepo;

  private final PaymentTypeRepository paymentTypeRepo;

  private final InstallationRepository installationRepo;

  private final IncomeRepository incomeRepo;

  private final UserRepository userRepo;

  private final MaintenanceRepository maintenanceRepo;

  private final MachineMapper mapper;

  @Transactional
  public Long createMachine(@NonNull MachineCreateDto dto) {
    Machine toSave = mapper.createToMachine(dto);
    Set<PaymentType> paymentTypes = new HashSet<>(paymentTypeRepo
        .findAllById(dto.paymentTypes()));
    toSave.setPaymentTypes(paymentTypes);
    Machine save = machineRepo.save(toSave);
    return save.getId();
  }

  @Transactional
  public Long createMaintenance(@NonNull MaintenanceCreateDto dto) {
    Maintenance toSave = mapper.createToMaintenance(dto);
    Machine machine = machineRepo.findById(dto.machineId())
        .orElseThrow(() -> new EntityNotFoundException("No suitable machine"));
    User user = userRepo.findById(dto.userId())
        .orElseThrow(() -> new EntityNotFoundException("No suitable user"));
    toSave.setMachine(machine);
    toSave.setUser(user);
    Maintenance saved = maintenanceRepo.save(toSave);
    return saved.getId();
  }

  @Transactional
  public Long createPaymentType(@NonNull PaymentTypeCreateDto dto) {
    PaymentType toSave = mapper.createToPaymentType(dto);
    PaymentType saved = paymentTypeRepo.save(toSave);
    return saved.getId();
  }

  @Transactional
  public void changeInstallation(InstallationCreateDto dto) {
    Installation installation = mapper.createToInstallation(dto);
    Machine machine = machineRepo.findById(dto.machineId())
        .orElseThrow(() -> new EntityNotFoundException("No suitable machine found."));
    installation.setActive(true);
    machine.getInstallations().forEach(i -> i.setActive(false));
    machine.addInstallation(installation);
    Income income = new Income();
    income.setInstallation(installation);
    installation.setIncome(income);
    incomeRepo.save(income);
    installationRepo.save(installation);
  }

  @Transactional(readOnly = true)
  public MachineReadDto findMachine(long id) {
    Machine machine = machineRepo.findMachineWithPaymentTypes(id)
        .orElseThrow(
            () -> new EntityNotFoundException("Machine with ID=%d not found"
                .formatted(id)));
    return mapper.machineToRead(machine);
  }

  @Transactional
  public void updateMachine(@NonNull MachineUpdateDto dto) {
    Machine machine = machineRepo.findById(dto.id())
        .orElseThrow(
            () -> new EntityNotFoundException("Machine with ID=%d not found"
                .formatted(dto.id())));
    Set<PaymentType> paymentTypes =
        new HashSet<>(paymentTypeRepo.findAllById(dto.paymentTypeIds()));
    if (machine.getPaymentTypes().size() != paymentTypes.size()) {
      addPaymentTypes(machine, paymentTypes);
    }
    mapper.updateMachineFromDto(dto, machine);
  }

  @Transactional
  public void deleteMachine(long id) {
    machineRepo.deleteById(id);
  }

  private void addPaymentTypes(@NonNull Machine machine,
      @NonNull Set<PaymentType> paymentTypes) {
    machine.getPaymentTypes().clear();
    machine.getPaymentTypes().addAll(paymentTypes);
  }
}