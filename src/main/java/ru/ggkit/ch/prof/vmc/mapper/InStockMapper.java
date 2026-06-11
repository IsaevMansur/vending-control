package ru.ggkit.ch.prof.vmc.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.ggkit.ch.prof.vmc.dto.create.InStockCreateDto;
import ru.ggkit.ch.prof.vmc.dto.read.InStockReadDto;
import ru.ggkit.ch.prof.vmc.dto.update.InStockUpdateDto;
import ru.ggkit.ch.prof.vmc.entity.InStock;

@Mapper(componentModel = "spring")
public interface InStockMapper {

  @Mapping(target = "product", ignore = true)
  @Mapping(target = "machine", ignore = true)
  @Mapping(target = "id", ignore = true)
  InStock createDtoToInstock(InStockCreateDto inStockCreateDto);

  InStockReadDto inStockToReadDto(InStock inStock);

  @Mapping(target = "product", ignore = true)
  @Mapping(target = "machine", ignore = true)
  @Mapping(target = "id", ignore = true)
  InStock updateDtoToInStock(InStockUpdateDto inStockUpdateDto);
}
