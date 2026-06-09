package ru.ggkit.ch.prof.vmc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ggkit.ch.prof.vmc.dto.create.SaleCreateDto;
import ru.ggkit.ch.prof.vmc.mapper.SaleMapper;
import ru.ggkit.ch.prof.vmc.repository.SaleRepository;

@Service
@RequiredArgsConstructor
public class DefaultSaleService {

  private final SaleRepository repSale;

  private final SaleMapper mapper;

  public Long createSale(SaleCreateDto saleCreateDto) {
    return null;
  }
}