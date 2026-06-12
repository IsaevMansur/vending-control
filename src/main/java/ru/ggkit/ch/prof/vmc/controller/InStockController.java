package ru.ggkit.ch.prof.vmc.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ggkit.ch.prof.vmc.dto.create.InStockCreateDto;
import ru.ggkit.ch.prof.vmc.dto.read.InStockReadDto;
import ru.ggkit.ch.prof.vmc.dto.update.InStockUpdateDto;
import ru.ggkit.ch.prof.vmc.service.InStockService;

@RestController
@RequestMapping("in-stocks")
@RequiredArgsConstructor
public class InStockController {

  private final InStockService inStockService;

  @PostMapping
  public ResponseEntity<InStockReadDto> createInStock(
      @RequestBody @Valid InStockCreateDto inStockCreateDto) {
    InStockReadDto saved = inStockService.saveInStock(inStockCreateDto);
    return ResponseEntity.ok(saved);
  }

  @GetMapping("{id}")
  public ResponseEntity<InStockReadDto> findInStock(@PathVariable long id) {
    InStockReadDto found = inStockService.findInStock(id);
    return ResponseEntity.ok(found);
  }

  @PutMapping("")
  public ResponseEntity<Void> updateInStock(@RequestBody InStockUpdateDto inStockUpdateDto) {
    inStockService.updateInStock(inStockUpdateDto);
    return ResponseEntity.ok().build();
  }
}
