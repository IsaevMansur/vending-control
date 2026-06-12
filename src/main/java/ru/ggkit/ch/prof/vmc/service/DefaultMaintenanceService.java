package ru.ggkit.ch.prof.vmc.service;

import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ggkit.ch.prof.vmc.dto.create.MaintenanceCreateDto;
import ru.ggkit.ch.prof.vmc.dto.read.MaintenanceReadDto;
import ru.ggkit.ch.prof.vmc.entity.Maintenance;
import ru.ggkit.ch.prof.vmc.entity.projection.MaintenanceProjection;
import ru.ggkit.ch.prof.vmc.mapper.MaintenanceMapper;
import ru.ggkit.ch.prof.vmc.repository.MaintenanceRepository;

@Service
@RequiredArgsConstructor
public class DefaultMaintenanceService implements MaintenanceService {

  private final MaintenanceRepository repoMaintenance;
  private final MaintenanceMapper maintenanceMapper;

  @Override
  @Transactional
  public MaintenanceReadDto saveMaintenance(MaintenanceCreateDto maintenanceCreateDto) {
    Maintenance maintenance = maintenanceMapper.createDtoToMaintenance(maintenanceCreateDto);
    MaintenanceProjection projection = repoMaintenance.findMaintenanceRequiredPropsOrThrow(
        maintenanceCreateDto.machineId(),
        maintenanceCreateDto.userId());
    buildMaintenance(maintenance, projection);
    Maintenance saved = repoMaintenance.save(maintenance);
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

  private void buildMaintenance(
      @NonNull Maintenance maintenance,
      @NonNull MaintenanceProjection projection) {
    maintenance.setUser(projection.getUser());
    maintenance.setMachine(projection.getMachine());
  }
}
