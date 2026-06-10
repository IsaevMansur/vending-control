package ru.ggkit.ch.prof.vmc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ggkit.ch.prof.vmc.dto.create.InStockCreateDto;
import ru.ggkit.ch.prof.vmc.dto.create.ProductCreateDto;
import ru.ggkit.ch.prof.vmc.dto.read.ProductReadDto;
import ru.ggkit.ch.prof.vmc.dto.update.PriceUpdateDto;
import ru.ggkit.ch.prof.vmc.dto.update.ProductUpdateDto;
import ru.ggkit.ch.prof.vmc.entity.InStock;
import ru.ggkit.ch.prof.vmc.entity.Machine;
import ru.ggkit.ch.prof.vmc.entity.Price;
import ru.ggkit.ch.prof.vmc.entity.Product;
import ru.ggkit.ch.prof.vmc.entity.projection.InStockProjection;
import ru.ggkit.ch.prof.vmc.mapper.ProductMapper;
import ru.ggkit.ch.prof.vmc.repository.InStockRepository;
import ru.ggkit.ch.prof.vmc.repository.ProductRepository;

@Service
@RequiredArgsConstructor
public class DefaultProductService implements ProductService {

  private final ProductRepository repoProduct;
  private final InStockRepository repoInStock;
  private final ProductMapper productMapper;

  @Override
  @Transactional
  public Long createProduct(ProductCreateDto request) {
    Product product = productMapper.createToProduct(request);
    Price price = productMapper.createToPrice(request.price());
    product.addPrice(price);
    Product saved = repoProduct.save(product);
    return saved.getId();
  }

  @Override
  @Transactional
  public Long createInStock(InStockCreateDto request) {
    InStockProjection projection = repoInStock.findInStockRequiredProps(request.machineId(),
        request.productId())
        .orElseThrow();
    InStock inStock = productMapper.createToInStock(request);
    Product product = projection.getProduct();
    Machine machine = projection.getMachine();
    inStock.setProduct(product);
    inStock.setMachine(machine);
    product.addInStock(inStock);
    InStock saved = repoInStock.save(inStock);
    return saved.getId();
  }

  @Override
  @Transactional
  public void addPrice(PriceUpdateDto priceUpdateDto) {
    Price price = productMapper.updateToPrice(priceUpdateDto);
    Product product = repoProduct.findProduct(priceUpdateDto.id());
    product.getPrices().forEach(e -> e.setActive(false));
    price.setActive(true);
    product.addPrice(price);
  }

  @Override
  @Transactional(readOnly = true)
  public ProductReadDto findProduct(long id) {
    Product product = repoProduct.findProduct(id);
    return productMapper.productToRead(product);
  }

  @Override
  @Transactional
  public void updateProduct(ProductUpdateDto productUpdateDto) {
    Product product = repoProduct.findProductWithPricesOrThrow(productUpdateDto.id());
    productMapper.updateProductFromDto(productUpdateDto, product);
  }

  @Override
  @Transactional
  public void deleteProduct(long id) {
    repoProduct.deleteById(id);
  }
}