package ru.ggkit.ch.prof.vmc.controller;

import jakarta.validation.Valid;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import ru.ggkit.ch.prof.vmc.dto.create.SaleCreateDto;
import ru.ggkit.ch.prof.vmc.dto.read.SaleReadDto;
import ru.ggkit.ch.prof.vmc.service.SaleService;

@RestController
@RequestMapping("sales")
@RequiredArgsConstructor
public class SaleController {

  private final SaleService saleService;

  @PostMapping
  public ResponseEntity<SaleReadDto> createSale(
      @RequestBody @Valid SaleCreateDto request,
      UriComponentsBuilder builder) {
    var saved = saleService.createSale(request);
    URI uri = builder.path("{id}").buildAndExpand(saved.id()).toUri();
    return ResponseEntity.created(uri).body(saved);
  }

  @GetMapping("{id}")
  public ResponseEntity<SaleReadDto> findSale(@PathVariable long id) {
    SaleReadDto sale = saleService.findSale(id);
    return ResponseEntity.ok(sale);
  }
}
