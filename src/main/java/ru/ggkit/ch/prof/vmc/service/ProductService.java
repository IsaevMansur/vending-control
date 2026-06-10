package ru.ggkit.ch.prof.vmc.service;

import ru.ggkit.ch.prof.vmc.dto.create.InStockCreateDto;
import ru.ggkit.ch.prof.vmc.dto.create.ProductCreateDto;
import ru.ggkit.ch.prof.vmc.dto.read.ProductReadDto;
import ru.ggkit.ch.prof.vmc.dto.update.PriceUpdateDto;
import ru.ggkit.ch.prof.vmc.dto.update.ProductUpdateDto;

public interface ProductService {

  Long createProduct(ProductCreateDto request);

  Long createInStock(InStockCreateDto request);

  void addPrice(PriceUpdateDto request);

  ProductReadDto findProduct(long id);

  void updateProduct(ProductUpdateDto request);

  void deleteProduct(long id);
}
