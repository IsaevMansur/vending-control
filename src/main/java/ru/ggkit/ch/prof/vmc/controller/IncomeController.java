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
import ru.ggkit.ch.prof.vmc.dto.create.IncomeCreateDto;
import ru.ggkit.ch.prof.vmc.dto.read.IncomeReadDto;
import ru.ggkit.ch.prof.vmc.service.IncomeService;

@RestController
@RequestMapping("incomes")
@RequiredArgsConstructor
public class IncomeController {

  private final IncomeService incomeService;

  @PostMapping
  public ResponseEntity<IncomeReadDto> createIncome(
      @RequestBody @Valid IncomeCreateDto request) {
    IncomeReadDto saved = incomeService.saveIncome(request);
    return ResponseEntity.ok(saved);
  }

  @GetMapping("{id}")
  public ResponseEntity<IncomeReadDto> findIncome(
      @PathVariable long id) {
    IncomeReadDto found = incomeService.findIncome(id);
    return ResponseEntity.ok(found);
  }

  @GetMapping("machine/{machineId}")
  public ResponseEntity<Set<IncomeReadDto>> findIncomesForMachine(
      @PathVariable long machineId) {
    Set<IncomeReadDto> founds = incomeService.findIncomesForMachine(machineId);
    return ResponseEntity.ok(founds);
  }
}
