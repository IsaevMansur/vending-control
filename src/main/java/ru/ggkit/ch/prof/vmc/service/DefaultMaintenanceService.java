package ru.ggkit.ch.prof.vmc.service;

import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ggkit.ch.prof.vmc.dto.create.MaintenanceCreateDto;
import ru.ggkit.ch.prof.vmc.dto.create.MaintenanceReadDto;
import ru.ggkit.ch.prof.vmc.entity.Maintenance;
import ru.ggkit.ch.prof.vmc.mapper.MaintenanceMapper;
import ru.ggkit.ch.prof.vmc.repository.MaintenanceRepository;

@Service
@RequiredArgsConstructor
public class DefaultMaintenanceService implements MaintenanceService {

  private final MaintenanceRepository repoMaintenance;
  private final MaintenanceMapper maintenanceMapper;

  @Override
  @Transactional
  public MaintenanceReadDto createMaintenance(MaintenanceCreateDto maintenanceCreateDto) {
    Maintenance toSave = maintenanceMapper.createDtoToMaintenance(maintenanceCreateDto);
    Maintenance saved = repoMaintenance.save(toSave);
    return maintenanceMapper.maintenanceToReadDto(saved);
  }

  @Override
  @Transactional(readOnly = true)
  public MaintenanceReadDto findMaintenance(long id) {
    Maintenance maintenance = repoMaintenance.findMaintenanceOrThrow(id);
    return maintenanceMapper.maintenanceToReadDto(maintenance);
  }

  @Override
  @Transactional(readOnly = true)
  public Set<MaintenanceReadDto> findAllForMachine(long machineId) {
    Set<Maintenance> machines = repoMaintenance.findAllForMachine(machineId);
    return machines.stream()
        .map(maintenanceMapper::maintenanceToReadDto)
        .collect(Collectors.toSet());
  }
}
