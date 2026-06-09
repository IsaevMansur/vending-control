package ru.ggkit.ch.prof.vmc.service;

import org.jspecify.annotations.NonNull;
import org.springframework.transaction.annotation.Transactional;
import ru.ggkit.ch.prof.vmc.dto.create.InStockCreateDto;
import ru.ggkit.ch.prof.vmc.dto.create.ProductCreateDto;
import ru.ggkit.ch.prof.vmc.dto.read.InStockReadDto;
import ru.ggkit.ch.prof.vmc.dto.read.ProductReadDto;
import ru.ggkit.ch.prof.vmc.dto.update.InStockUpdateDto;
import ru.ggkit.ch.prof.vmc.dto.update.PriceUpdateDto;
import ru.ggkit.ch.prof.vmc.dto.update.ProductUpdateDto;

public interface ProductService {

  @Transactional
  Long createProduct(ProductCreateDto request);

  @Transactional
  Long createInStock(@NonNull InStockCreateDto request);

  @Transactional
  void changeInStock(@NonNull InStockUpdateDto request);

  @Transactional(readOnly = true)
  InStockReadDto findInStock(long id);

  @Transactional
  void addPrice(PriceUpdateDto request);

  @Transactional(readOnly = true)
  ProductReadDto findProduct(long id);

  @Transactional
  void updateProduct(@NonNull ProductUpdateDto request);

  @Transactional
  void deleteProduct(long id);
}
