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

  @Mapping(target = "machineId", source = "machine", expression = "java(machine.getId())")
  @Mapping(target = "productId", source = "product", expression = "java(product.getId())")
  @Mapping(target = "paymentTypeId", source = "paymentType", expression = "java(paymentType.getId())")
  SaleReadDto saleToRead(Sale sale);
}