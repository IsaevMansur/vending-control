package ru.ggkit.ch.prof.vmc.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.ggkit.ch.prof.vmc.dto.create.SaleCreateDto;
import ru.ggkit.ch.prof.vmc.dto.read.SaleReadDto;
import ru.ggkit.ch.prof.vmc.entity.Sale;

@Mapper(
    componentModel = "spring",
    uses = {
        MachineMapper.class,
        ProductMapper.class,
        PaymentTypeMapper.class
    }
)
public interface SaleMapper {

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "product", ignore = true)
  @Mapping(target = "paymentType", ignore = true)
  @Mapping(target = "machine", ignore = true)
  Sale createDtoToSale(SaleCreateDto saleCreateDto);

  SaleReadDto saleToReadDto(Sale sale);
}