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
import ru.ggkit.ch.prof.vmc.dto.create.InStockCreateDto;
import ru.ggkit.ch.prof.vmc.dto.create.ProductCreateDto;
import ru.ggkit.ch.prof.vmc.dto.read.InStockReadDto;
import ru.ggkit.ch.prof.vmc.dto.read.ProductReadDto;
import ru.ggkit.ch.prof.vmc.dto.update.InStockUpdateDto;
import ru.ggkit.ch.prof.vmc.dto.update.PriceUpdateDto;
import ru.ggkit.ch.prof.vmc.dto.update.ProductUpdateDto;
import ru.ggkit.ch.prof.vmc.service.ProductService;

@RestController
@RequestMapping("products")
@RequiredArgsConstructor
public class ProductController {

  private final ProductService service;

  @PostMapping("product")
  public ResponseEntity<Long> createProduct(
      @RequestBody @Valid ProductCreateDto request,
      UriComponentsBuilder builder) {
    Long product = service.createProduct(request);
    URI uri = builder.path("{id}").buildAndExpand(product).toUri();
    return ResponseEntity.created(uri).body(product);
  }

  @PatchMapping("in-stock/")
  public ResponseEntity<Void> changeInStock(@RequestBody @Valid InStockUpdateDto inStockUpdateDto) {
    service.changeInStock(inStockUpdateDto);
    return ResponseEntity.ok().build();
  }

  @GetMapping("in-stock/{id}")
  public ResponseEntity<InStockReadDto> findInStock(@PathVariable Long id) {
    InStockReadDto inStock = service.findInStock(id);
    return ResponseEntity.ok(inStock);
  }

  @PostMapping("in-stock")
  public ResponseEntity<Long> createInStock(@RequestBody @Valid InStockCreateDto request,
      UriComponentsBuilder builder) {
    Long inStock = service.createInStock(request);
    URI uri = builder.path("in-stock/{id}").buildAndExpand(inStock).toUri();
    return ResponseEntity.created(uri).body(inStock);
  }

  @PatchMapping("price")
  public ResponseEntity<Void> addPrice(
      @RequestBody @Valid PriceUpdateDto priceUpdateDto) {
    service.addPrice(priceUpdateDto);
    return ResponseEntity.ok().build();
  }

  @GetMapping("product/{id}")
  public ResponseEntity<ProductReadDto> findProduct(@PathVariable long id) {
    ProductReadDto product = service.findProduct(id);
    return ResponseEntity.ok(product);
  }

  @PutMapping("product")
  public ResponseEntity<Void> updateProduct(@RequestBody @Valid ProductUpdateDto productUpdateDto) {
    service.updateProduct(productUpdateDto);
    return ResponseEntity.ok().build();
  }

  @DeleteMapping("product/{id}")
  public ResponseEntity<Void> deleteProduct(@PathVariable long id) {
    service.deleteProduct(id);
    return ResponseEntity.noContent().build();
  }
}