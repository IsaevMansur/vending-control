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
import ru.ggkit.ch.prof.vmc.dto.create.ProductCreateDto;
import ru.ggkit.ch.prof.vmc.dto.read.ProductReadDto;
import ru.ggkit.ch.prof.vmc.dto.update.ProductUpdateDto;
import ru.ggkit.ch.prof.vmc.service.ProductService;

@RestController
@RequestMapping("products")
@RequiredArgsConstructor
public class ProductController {

  private final ProductService productService;

  @PostMapping("product")
  public ResponseEntity<ProductReadDto> createProduct(
      @RequestBody @Valid ProductCreateDto request) {
    ProductReadDto saved = productService.saveProduct(request);
    return ResponseEntity.ok(saved);
  }

  @GetMapping("product/{id}")
  public ResponseEntity<ProductReadDto> findProduct(@PathVariable long id) {
    ProductReadDto product = productService.findProduct(id);
    return ResponseEntity.ok(product);
  }

  @PutMapping("product")
  public ResponseEntity<Void> updateProduct(
      @RequestBody @Valid ProductUpdateDto productUpdateDto) {
    productService.updateProduct(productUpdateDto);
    return ResponseEntity.ok().build();
  }

  @DeleteMapping("product/{id}")
  public ResponseEntity<Void> deleteProduct(@PathVariable long id) {
    productService.deleteProduct(id);
    return ResponseEntity.noContent().build();
  }
}