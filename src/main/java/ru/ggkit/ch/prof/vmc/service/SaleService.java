package ru.ggkit.ch.prof.vmc.service;

import org.springframework.transaction.annotation.Transactional;
import ru.ggkit.ch.prof.vmc.dto.create.SaleCreateDto;
import ru.ggkit.ch.prof.vmc.dto.read.SaleReadDto;

public interface SaleService {

  @Transactional
  Long createSale(SaleCreateDto saleCreateDto);

  @Transactional(readOnly = true)
  SaleReadDto findSale(long id);
}
