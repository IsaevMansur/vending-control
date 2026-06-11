package ru.ggkit.ch.prof.vmc.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.ggkit.ch.prof.vmc.dto.create.ProductCreateDto;
import ru.ggkit.ch.prof.vmc.dto.read.ProductReadDto;
import ru.ggkit.ch.prof.vmc.dto.update.ProductUpdateDto;
import ru.ggkit.ch.prof.vmc.entity.Product;

@Mapper(
    componentModel = "spring",
    uses = {
        InStockMapper.class
    }
)
public interface ProductMapper {

  @Mapping(target = "id", ignore = true)
  Product createDtoToProduct(ProductCreateDto productCreateDto);

  ProductReadDto productToReadDto(Product product);

  Product updateDtoToProduct(ProductUpdateDto productUpdateDto);

  void updateProductFromDto(ProductUpdateDto productUpdateDto, @MappingTarget Product product);
}