package ru.ggkit.ch.prof.vmc.mapper;

import java.util.Set;
import org.jspecify.annotations.NonNull;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import ru.ggkit.ch.prof.vmc.dto.create.ProductCreateDto;
import ru.ggkit.ch.prof.vmc.dto.read.ProductReadDto;
import ru.ggkit.ch.prof.vmc.dto.update.ProductUpdateDto;
import ru.ggkit.ch.prof.vmc.entity.Price;
import ru.ggkit.ch.prof.vmc.entity.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {

  @Mapping(target = "id", ignore = true)
  Product createDtoToProduct(ProductCreateDto dto);

  @Mapping(target = "price", source = "prices", qualifiedByName = "getActivePrice")
  ProductReadDto productToReadDto(Product product);

  Product updateDtoToProduct(ProductUpdateDto dto);

  @Mapping(target = "id", ignore = true)
  void updateProductFromDto(ProductUpdateDto dto, @MappingTarget Product product);

  @Named("getActivePrice")
  default Price getActivePrice(@NonNull Set<Price> prices) {
    return prices.stream()
        .filter(Price::isActive)
        .findFirst().orElse(null);
  }
}