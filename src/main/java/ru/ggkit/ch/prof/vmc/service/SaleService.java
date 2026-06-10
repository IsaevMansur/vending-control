package ru.ggkit.ch.prof.vmc.service;

import ru.ggkit.ch.prof.vmc.dto.create.SaleCreateDto;
import ru.ggkit.ch.prof.vmc.dto.read.SaleReadDto;

public interface SaleService {

  SaleReadDto createSale(SaleCreateDto saleCreateDto);

  SaleReadDto findSale(long id);
}
