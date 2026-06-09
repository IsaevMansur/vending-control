package ru.ggkit.ch.prof.vmc.service;

import org.jspecify.annotations.NonNull;
import org.springframework.transaction.annotation.Transactional;
import ru.ggkit.ch.prof.vmc.dto.create.ProductCreateDto;
import ru.ggkit.ch.prof.vmc.dto.read.ProductReadDto;
import ru.ggkit.ch.prof.vmc.dto.update.PriceUpdateDto;
import ru.ggkit.ch.prof.vmc.dto.update.ProductUpdateDto;

public interface ProductService {

  @Transactional
  Long createProduct(ProductCreateDto request);

  @Transactional
  void addPrice(PriceUpdateDto request);

  @Transactional(readOnly = true)
  ProductReadDto findProduct(long id);

  @Transactional
  void updateProduct(@NonNull ProductUpdateDto request);

  @Transactional
  void deleteProduct(long id);
}
