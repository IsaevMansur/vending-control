package ru.ggkit.ch.prof.vmc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ggkit.ch.prof.vmc.dto.create.SaleCreateDto;
import ru.ggkit.ch.prof.vmc.dto.read.SaleReadDto;
import ru.ggkit.ch.prof.vmc.entity.Sale;
import ru.ggkit.ch.prof.vmc.entity.projection.SaleProjection;
import ru.ggkit.ch.prof.vmc.exception.EntityNotFoundException;
import ru.ggkit.ch.prof.vmc.exception.SubEntityNotFoundException;
import ru.ggkit.ch.prof.vmc.mapper.SaleMapper;
import ru.ggkit.ch.prof.vmc.repository.SaleRepository;

@Service
@RequiredArgsConstructor
public class DefaultSaleService implements SaleService {

  private final SaleRepository repSale;

  private final SaleMapper mapper;

  @Transactional
  @Override
  public Long createSale(SaleCreateDto saleCreateDto) {
    Sale sale = mapper.createToSale(saleCreateDto);
    SaleProjection projection = repSale.findSaleRequiredProps(saleCreateDto.machineId(),
            saleCreateDto.productId(), saleCreateDto.paymentTypeId())
        .orElseThrow(() -> new SubEntityNotFoundException("Failed to load required data for sale"));
    sale.setMachine(projection.getMachine());
    sale.setProduct(projection.getProduct());
    sale.setPaymentType(projection.getPaymentType());
    Sale save = repSale.save(sale);
    return save.getId();
  }

  @Transactional(readOnly = true)
  @Override
  public SaleReadDto findSale(long id) {
    Sale sale = repSale.findFullSale(id)
        .orElseThrow(() -> EntityNotFoundException.of(Sale.class, id));
    return mapper.saleToRead(sale);
  }
}