package ru.ggkit.ch.prof.vmc.service;

import java.util.Set;
import ru.ggkit.ch.prof.vmc.dto.create.MaintenanceCreateDto;
import ru.ggkit.ch.prof.vmc.dto.create.MaintenanceReadDto;

public interface MaintenanceService {

  MaintenanceReadDto createMaintenance(MaintenanceCreateDto maintenanceCreateDto);
  MaintenanceReadDto findMaintenance(long id);
  Set<MaintenanceReadDto> findAllForMachine(long machineId);
}
