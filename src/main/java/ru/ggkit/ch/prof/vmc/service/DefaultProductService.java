package ru.ggkit.ch.prof.vmc.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ggkit.ch.prof.vmc.dto.create.ProductCreateDto;
import ru.ggkit.ch.prof.vmc.dto.read.ProductReadDto;
import ru.ggkit.ch.prof.vmc.dto.update.PriceUpdateDto;
import ru.ggkit.ch.prof.vmc.dto.update.ProductUpdateDto;
import ru.ggkit.ch.prof.vmc.entity.Price;
import ru.ggkit.ch.prof.vmc.entity.Product;
import ru.ggkit.ch.prof.vmc.mapper.ProductMapper;
import ru.ggkit.ch.prof.vmc.repository.PriceRepository;
import ru.ggkit.ch.prof.vmc.repository.ProductRepository;

@Service
@RequiredArgsConstructor
public class DefaultProductService implements ProductService {

  private final ProductRepository repProduct;

  private final PriceRepository repPrice;

  private final ProductMapper mapper;

  @Transactional
  @Override
  public Long createProduct(ProductCreateDto request) {
    Product product = mapper.createToProduct(request);
    Price price = mapper.createToPrice(request.price());
    Product saved = repProduct.save(product);
    repPrice.save(price);
    return saved.getId();
  }

  @Transactional
  @Override
  public void addPrice(PriceUpdateDto request) {
    Price price = mapper.updateToPrice(request);
    Product product = repProduct.findById(request.productId())
        .orElseThrow(() -> new EntityNotFoundException("No suitable product found"));
    product.getPrices().forEach(e -> e.setActive(false));
    price.setActive(true);
    product.addPrice(price);
  }

  @Transactional(readOnly = true)
  @Override
  public ProductReadDto findProduct(long id) {
    Product product = repProduct.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Product not found"));
    return mapper.productToRead(product);
  }

  @Transactional
  @Override
  public void updateProduct(@NonNull ProductUpdateDto request) {
    Product product = repProduct.findProductWithPrices(request.id())
        .orElseThrow(() -> new EntityNotFoundException("Product not found"));
    mapper.updateFromDto(request, product);
  }

  @Transactional
  @Override
  public void deleteProduct(long id) {
    repProduct.deleteById(id);
  }
}