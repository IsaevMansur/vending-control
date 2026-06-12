package ru.ggkit.ch.prof.vmc.service;

import ru.ggkit.ch.prof.vmc.dto.create.InStockCreateDto;
import ru.ggkit.ch.prof.vmc.dto.read.InStockReadDto;
import ru.ggkit.ch.prof.vmc.dto.update.InStockUpdateDto;

public interface InStockService {

  InStockReadDto saveInStock(InStockCreateDto inStockCreateDto);

  InStockReadDto findInStock(long id);

  void updateInStock(InStockUpdateDto inStockUpdateDto);
}
