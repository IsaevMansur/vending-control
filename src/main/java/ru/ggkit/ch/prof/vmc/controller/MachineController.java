package ru.ggkit.ch.prof.vmc.controller;

import jakarta.validation.Valid;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import ru.ggkit.ch.prof.vmc.dto.create.InstallationCreateDto;
import ru.ggkit.ch.prof.vmc.dto.create.MachineCreateDto;
import ru.ggkit.ch.prof.vmc.dto.create.PaymentTypeCreateDto;
import ru.ggkit.ch.prof.vmc.dto.read.MachineReadDto;
import ru.ggkit.ch.prof.vmc.dto.update.MachineUpdateDto;
import ru.ggkit.ch.prof.vmc.service.MachineService;

@RestController
@RequestMapping("machines")
@RequiredArgsConstructor
public class MachineController {

  private final MachineService service;

  @PostMapping
  public ResponseEntity<Long> createMachine(
      @RequestBody @Valid MachineCreateDto machineCreateDto,
      UriComponentsBuilder builder) {
    Long savedId = service.createMachine(machineCreateDto);
    URI uri = builder.path("{id}").buildAndExpand(savedId).toUri();
    return ResponseEntity.created(uri).body(savedId);
  }

  @PostMapping("paymentType")
  public ResponseEntity<Long> createPaymentType(
      @RequestBody @Valid PaymentTypeCreateDto paymentTypeCreateDto,
      UriComponentsBuilder builder) {
    Long savedId = service.createPaymentType(paymentTypeCreateDto);
    URI uri = builder.path("{id}").buildAndExpand(savedId).toUri();
    return ResponseEntity.created(uri).body(savedId);
  }

  @PatchMapping("installation")
  public ResponseEntity<Void> changeInstallation(
      @RequestBody @Valid InstallationCreateDto installationCreateDto) {
    service.changeInstallation(installationCreateDto);
    return ResponseEntity.ok().build();
  }

  @GetMapping("{id}")
  public ResponseEntity<MachineReadDto> findMachine(@PathVariable Long id) {
    MachineReadDto machine = service.findMachine(id);
    return ResponseEntity.ok(machine);
  }

  @PutMapping
  public ResponseEntity<Void> updateMachine(
      @RequestBody @Valid MachineUpdateDto machineUpdateDto) {
    service.updateMachine(machineUpdateDto);
    return ResponseEntity.ok().build();
  }

  @DeleteMapping("{id}")
  public ResponseEntity<Void> deleteMachine(@PathVariable Long id) {
    service.deleteMachine(id);
    return ResponseEntity.ok().build();
  }
}