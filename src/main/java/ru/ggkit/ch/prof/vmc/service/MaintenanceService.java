package ru.ggkit.ch.prof.vmc.service;

import java.util.Set;
import ru.ggkit.ch.prof.vmc.dto.create.MaintenanceCreateDto;
import ru.ggkit.ch.prof.vmc.dto.read.MaintenanceReadDto;

public interface MaintenanceService {

  MaintenanceReadDto saveMaintenance(MaintenanceCreateDto maintenanceCreateDto);

  MaintenanceReadDto findMaintenance(long id);

  Set<MaintenanceReadDto> findAllForMachine(long machineId);
}
