package ru.ggkit.ch.prof.vmc.mapper;

import java.util.Set;
import org.jspecify.annotations.NonNull;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import ru.ggkit.ch.prof.vmc.dto.create.PriceCreateDto;
import ru.ggkit.ch.prof.vmc.dto.create.ProductCreateDto;
import ru.ggkit.ch.prof.vmc.dto.read.PriceReadDto;
import ru.ggkit.ch.prof.vmc.dto.update.PriceUpdateDto;
import ru.ggkit.ch.prof.vmc.dto.update.ProductUpdateDto;
import ru.ggkit.ch.prof.vmc.entity.Price;
import ru.ggkit.ch.prof.vmc.entity.Product;
import ru.ggkit.ch.prof.vmc.dto.read.ProductReadDto;

@Mapper(componentModel = "spring")
public interface ProductMapper {

  @Mapping(target = "id", ignore = true)
  Product createToProduct(ProductCreateDto dto);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "product", ignore = true)
  @Mapping(target = "active", ignore = true)
  Price createToPrice(PriceCreateDto dto);

  @Mapping(target = "product", ignore = true)
  @Mapping(target = "active", ignore = true)
  Price updateToPrice(PriceUpdateDto request);

  @Mapping(target = "price", source = "prices", qualifiedByName = "getActivePrice")
  ProductReadDto productToRead(Product product);

  PriceReadDto priceToRead(Price price);


  Product updateToProduct(ProductUpdateDto dto);

  @Mapping(target = "id", ignore = true)
  void updateFromDto(ProductUpdateDto dto, @MappingTarget Product product);

  @Named("getActivePrice")
  default Price getActivePrice(@NonNull Set<Price> prices) {
    return prices.stream()
        .filter(Price::isActive)
        .findFirst().orElse(null);
  }
}