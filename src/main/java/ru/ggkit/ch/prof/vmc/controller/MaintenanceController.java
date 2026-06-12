package ru.ggkit.ch.prof.vmc.controller;

import jakarta.validation.Valid;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ggkit.ch.prof.vmc.dto.create.MaintenanceCreateDto;
import ru.ggkit.ch.prof.vmc.dto.read.MaintenanceReadDto;
import ru.ggkit.ch.prof.vmc.service.MaintenanceService;

@RestController
@RequestMapping("maintenance")
@RequiredArgsConstructor
public class MaintenanceController {

  private final MaintenanceService maintenanceService;

  @PostMapping
  public ResponseEntity<MaintenanceReadDto> createMaintenance(
      @RequestBody @Valid MaintenanceCreateDto maintenanceCreateDto) {
    MaintenanceReadDto maintenance = maintenanceService
        .saveMaintenance(maintenanceCreateDto);
    return ResponseEntity.ok(maintenance);
  }

  @GetMapping("{id}")
  public ResponseEntity<MaintenanceReadDto> findMaintenance(@PathVariable long id) {
    MaintenanceReadDto maintenance = maintenanceService.findMaintenance(id);
    return ResponseEntity.ok(maintenance);
  }

  @GetMapping("machine/{machineId}")
  public ResponseEntity<Set<MaintenanceReadDto>> findMaintenances(
      @PathVariable long machineId) {
    Set<MaintenanceReadDto> maintenances = maintenanceService
        .findAllForMachine(machineId);
    return ResponseEntity.ok(maintenances);
  }
}
