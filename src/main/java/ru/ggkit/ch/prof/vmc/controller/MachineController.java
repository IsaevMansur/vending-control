package ru.ggkit.ch.prof.vmc.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ggkit.ch.prof.vmc.dto.create.MachineCreateDto;
import ru.ggkit.ch.prof.vmc.dto.read.MachineReadDto;
import ru.ggkit.ch.prof.vmc.dto.update.MachineUpdateDto;
import ru.ggkit.ch.prof.vmc.service.MachineService;

@RestController
@RequestMapping("machines")
@RequiredArgsConstructor
public class MachineController {

  private final MachineService machineService;

  @PostMapping
  public ResponseEntity<MachineReadDto> createMachine(
      @RequestBody @Valid MachineCreateDto machineCreateDto) {
    MachineReadDto saved = machineService.createMachine(machineCreateDto);
    return ResponseEntity.ok(saved);
  }

  @GetMapping("{id}")
  public ResponseEntity<MachineReadDto> findMachine(@PathVariable Long id) {
    MachineReadDto machine = machineService.findMachine(id);
    return ResponseEntity.ok(machine);
  }

  @PutMapping
  public ResponseEntity<Void> updateMachine(
      @RequestBody @Valid MachineUpdateDto machineUpdateDto) {
    machineService.updateMachine(machineUpdateDto);
    return ResponseEntity.ok().build();
  }

  @DeleteMapping("{id}")
  public ResponseEntity<Void> deleteMachine(@PathVariable Long id) {
    machineService.deleteMachine(id);
    return ResponseEntity.ok().build();
  }
}