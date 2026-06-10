package ru.ggkit.ch.prof.vmc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ggkit.ch.prof.vmc.dto.create.SaleCreateDto;
import ru.ggkit.ch.prof.vmc.dto.read.SaleReadDto;
import ru.ggkit.ch.prof.vmc.entity.Sale;
import ru.ggkit.ch.prof.vmc.entity.projection.SaleProjection;
import ru.ggkit.ch.prof.vmc.mapper.SaleMapper;
import ru.ggkit.ch.prof.vmc.repository.SaleRepository;

@Service
@RequiredArgsConstructor
public class DefaultSaleService implements SaleService {

  private final SaleRepository repoSale;
  private final SaleMapper saleMapper;

  @Override
  @Transactional
  public SaleReadDto createSale(SaleCreateDto saleCreateDto) {
    SaleProjection projection = repoSale.findSaleRequiredPropsOrThrow(saleCreateDto.machineId(),
        saleCreateDto.productId(), saleCreateDto.paymentTypeId());
    Sale sale = saleMapper.createToSale(saleCreateDto);
    sale.setMachine(projection.getMachine());
    sale.setProduct(projection.getProduct());
    sale.setPaymentType(projection.getPaymentType());
    Sale saved = repoSale.save(sale);
    return saleMapper.saleToRead(saved);
  }

  @Override
  @Transactional(readOnly = true)
  public SaleReadDto findSale(long id) {
    Sale sale = repoSale.findFullSaleOrThrow(id);
    return saleMapper.saleToRead(sale);
  }
}