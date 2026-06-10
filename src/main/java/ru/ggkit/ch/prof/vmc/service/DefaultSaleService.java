package ru.ggkit.ch.prof.vmc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ggkit.ch.prof.vmc.dto.create.SaleCreateDto;
import ru.ggkit.ch.prof.vmc.dto.read.SaleReadDto;
import ru.ggkit.ch.prof.vmc.entity.Machine;
import ru.ggkit.ch.prof.vmc.entity.PaymentType;
import ru.ggkit.ch.prof.vmc.entity.Product;
import ru.ggkit.ch.prof.vmc.entity.Sale;
import ru.ggkit.ch.prof.vmc.exception.EntityNotFoundException;
import ru.ggkit.ch.prof.vmc.exception.SubEntityNotFoundException;
import ru.ggkit.ch.prof.vmc.mapper.SaleMapper;
import ru.ggkit.ch.prof.vmc.repository.MachineRepository;
import ru.ggkit.ch.prof.vmc.repository.PaymentTypeRepository;
import ru.ggkit.ch.prof.vmc.repository.ProductRepository;
import ru.ggkit.ch.prof.vmc.repository.SaleRepository;

@Service
@RequiredArgsConstructor
public class DefaultSaleService implements SaleService {

  private final SaleRepository repSale;

  private final MachineRepository repMachine;

  private final ProductRepository repProduct;

  private final PaymentTypeRepository repPaymentType;

  private final SaleMapper mapper;

  @Transactional
  @Override
  public Long createSale(SaleCreateDto saleCreateDto) {
    Sale sale = mapper.createToSale(saleCreateDto);
    Machine machine = repMachine.findById(saleCreateDto.machineId())
        .orElseThrow(() -> SubEntityNotFoundException.of(Machine.class, saleCreateDto.machineId()));
    Product product = repProduct.findById(saleCreateDto.productId())
        .orElseThrow(() -> SubEntityNotFoundException.of(Product.class, saleCreateDto.productId()));
    PaymentType paymentType = repPaymentType.findById(saleCreateDto.paymentTypeId())
        .orElseThrow(
            () -> SubEntityNotFoundException.of(PaymentType.class, saleCreateDto.paymentTypeId()));
    sale.setMachine(machine);
    sale.setProduct(product);
    sale.setPaymentType(paymentType);
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