package ru.ggkit.ch.prof.vmc.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.ggkit.ch.prof.vmc.dto.create.InStockCreateDto;
import ru.ggkit.ch.prof.vmc.dto.read.InStockReadDto;
import ru.ggkit.ch.prof.vmc.dto.update.InStockUpdateDto;
import ru.ggkit.ch.prof.vmc.entity.InStock;

@Mapper(componentModel = "spring")
public interface InStockMapper {

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "machine", ignore = true)
  @Mapping(target = "product", ignore = true)
  InStock createDtoToInStock(InStockCreateDto dto);

  @Mapping(target = "machineId", expression = "java(inStock.getMachine().getId())")
  @Mapping(target = "productId", expression = "java(inStock.getProduct().getId())")
  InStockReadDto inStockToReadDto(InStock inStock);

  @Mapping(target = "machine", ignore = true)
  @Mapping(target = "product", ignore = true)
  InStock updateDtoToInStock(InStockUpdateDto inStockUpdateDto);

  void updateInStockFromDto(InStockUpdateDto inStockUpdateDto, @MappingTarget InStock inStock);
}
