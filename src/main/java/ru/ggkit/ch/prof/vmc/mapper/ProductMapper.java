package ru.ggkit.ch.prof.vmc.mapper;

import java.util.Set;
import org.jspecify.annotations.NonNull;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import ru.ggkit.ch.prof.vmc.dto.create.InStockCreateDto;
import ru.ggkit.ch.prof.vmc.dto.create.PriceCreateDto;
import ru.ggkit.ch.prof.vmc.dto.create.ProductCreateDto;
import ru.ggkit.ch.prof.vmc.dto.read.InStockReadDto;
import ru.ggkit.ch.prof.vmc.dto.read.PriceReadDto;
import ru.ggkit.ch.prof.vmc.dto.update.InStockUpdateDto;
import ru.ggkit.ch.prof.vmc.dto.update.PriceUpdateDto;
import ru.ggkit.ch.prof.vmc.dto.update.ProductUpdateDto;
import ru.ggkit.ch.prof.vmc.entity.InStock;
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

  @Mapping(target = "id", ignore = true)
  InStock createToInStock(InStockCreateDto dto);

  @Mapping(target = "product", ignore = true)
  @Mapping(target = "active", ignore = true)
  Price updateToPrice(PriceUpdateDto request);

  @Mapping(target = "price", source = "prices", qualifiedByName = "getActivePrice")
  ProductReadDto productToRead(Product product);

  @Mapping(target = "machineId", source = "machine", expression = "java(machine.getId())")
  @Mapping(target = "productId", source = "product", expression = "java(product.getId())")
  InStockReadDto inStockToRead(InStock inStock);

  PriceReadDto priceToRead(Price price);


  Product updateToProduct(ProductUpdateDto dto);

  @Mapping(target = "id", ignore = true)
  void updateProductFromDto(ProductUpdateDto dto, @MappingTarget Product product);

  @Named("getActivePrice")
  default Price getActivePrice(@NonNull Set<Price> prices) {
    return prices.stream()
        .filter(Price::isActive)
        .findFirst().orElse(null);
  }

  @Mapping(target = "machine", ignore = true)
  @Mapping(target = "product", ignore = true)
  InStock updateToInStock(InStockUpdateDto request);

  void updateInStockFromDto(InStockUpdateDto dto, @MappingTarget InStock inStock);
}