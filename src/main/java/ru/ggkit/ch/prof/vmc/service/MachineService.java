package ru.ggkit.ch.prof.vmc.service;

import org.jspecify.annotations.NonNull;
import org.springframework.transaction.annotation.Transactional;
import ru.ggkit.ch.prof.vmc.dto.create.InstallationCreateDto;
import ru.ggkit.ch.prof.vmc.dto.create.MachineCreateDto;
import ru.ggkit.ch.prof.vmc.dto.create.MaintenanceCreateDto;
import ru.ggkit.ch.prof.vmc.dto.create.PaymentTypeCreateDto;
import ru.ggkit.ch.prof.vmc.dto.read.MachineReadDto;
import ru.ggkit.ch.prof.vmc.dto.update.MachineUpdateDto;

public interface MachineService {

  @Transactional
  Long createMachine(@NonNull MachineCreateDto dto);

  @Transactional
  Long createMaintenance(@NonNull MaintenanceCreateDto dto);

  @Transactional
  Long createPaymentType(@NonNull PaymentTypeCreateDto dto);

  @Transactional
  void changeInstallation(InstallationCreateDto dto);

  @Transactional(readOnly = true)
  MachineReadDto findMachine(long id);

  @Transactional
  void updateMachine(@NonNull MachineUpdateDto dto);

  @Transactional
  void deleteMachine(long id);
}
