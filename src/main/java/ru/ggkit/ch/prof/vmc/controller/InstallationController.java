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
import ru.ggkit.ch.prof.vmc.dto.create.InstallationCreateDto;
import ru.ggkit.ch.prof.vmc.dto.read.InstallationReadDto;
import ru.ggkit.ch.prof.vmc.service.InstallationService;

@RestController
@RequestMapping("installations")
@RequiredArgsConstructor
public class InstallationController {

  private final InstallationService installationService;

  @PostMapping("")
  public ResponseEntity<InstallationReadDto> createInstallation(
      @RequestBody @Valid InstallationCreateDto installationCreateDto) {
    InstallationReadDto saved = installationService.saveInstallation(
        installationCreateDto);
    return ResponseEntity.ok(saved);
  }

  @GetMapping("{id}")
  public ResponseEntity<InstallationReadDto> findInstallation(@PathVariable long id) {
    InstallationReadDto found = installationService.findInstallation(id);
    return ResponseEntity.ok(found);
  }

  @GetMapping("machine/{machineId}")
  public ResponseEntity<Set<InstallationReadDto>> findInstallationForMachine(
      @PathVariable long machineId) {
    Set<InstallationReadDto> founds = installationService.findInstallationsForMachine(
        machineId);
    return ResponseEntity.ok(founds);
  }
}
