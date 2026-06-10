package ru.ggkit.ch.prof.vmc.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.ggkit.ch.prof.vmc.dto.create.SaleCreateDto;
import ru.ggkit.ch.prof.vmc.dto.read.SaleReadDto;
import ru.ggkit.ch.prof.vmc.entity.Sale;

@Mapper(componentModel = "spring")
public interface SaleMapper {

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "machine", ignore = true)
  @Mapping(target = "product", ignore = true)
  @Mapping(target = "paymentType", ignore = true)
  Sale createToSale(SaleCreateDto dto);

  @Mapping(target = "machineId", expression = "java(sale.getMachine().getId())")
  @Mapping(target = "productId", expression = "java(sale.getProduct().getId())")
  @Mapping(target = "paymentTypeId", expression = "java(sale.getPaymentType().getId())")
  SaleReadDto saleToRead(Sale sale);
}