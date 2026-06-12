package ru.ggkit.ch.prof.vmc.service;

import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ggkit.ch.prof.vmc.dto.create.SaleCreateDto;
import ru.ggkit.ch.prof.vmc.dto.read.SaleReadDto;
import ru.ggkit.ch.prof.vmc.entity.InStock;
import ru.ggkit.ch.prof.vmc.entity.Income;
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
  public SaleReadDto saveSale(SaleCreateDto saleCreateDto) {
    SaleProjection projection = repoSale.findSaleRequiredPropsOrThrow(
        saleCreateDto.machineId(),
        saleCreateDto.productId(), saleCreateDto.paymentTypeId());
    Sale sale = saleMapper.createDtoToSale(saleCreateDto);
    Income income = projection.getIncome();
    BigDecimal price = projection.getProduct().getPrice();
    InStock inStock = projection.getInStock();
    applySale(income, saleCreateDto.soldCount(), price, inStock);
    buildSaleWith(sale, projection);
    Sale saved = repoSale.save(sale);
    return saleMapper.saleToReadDto(saved);
  }

  @Override
  @Transactional(readOnly = true)
  public SaleReadDto findSale(long id) {
    Sale sale = repoSale.findFullSaleOrThrow(id);
    return saleMapper.saleToReadDto(sale);
  }

  private void buildSaleWith(@NonNull Sale sale,
      @NonNull SaleProjection saleProjection) {
    sale.setMachine(saleProjection.getMachine());
    sale.setProduct(saleProjection.getProduct());
    sale.setPaymentType(saleProjection.getPaymentType());
  }

  private void applySale(
      @NonNull Income income,
      int soldCount,
      @NonNull BigDecimal price,
      @NonNull InStock inStock) {
    BigDecimal amount = price.multiply(BigDecimal.valueOf(soldCount));
    BigDecimal value = income.getValue();
    income.setValue(value.add(amount));
    int minus = inStock.getStock() - soldCount;
    inStock.setStock(minus);
  }
}