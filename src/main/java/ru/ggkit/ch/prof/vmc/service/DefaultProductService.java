package ru.ggkit.ch.prof.vmc.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ggkit.ch.prof.vmc.dto.create.InStockCreateDto;
import ru.ggkit.ch.prof.vmc.dto.create.ProductCreateDto;
import ru.ggkit.ch.prof.vmc.dto.read.InStockReadDto;
import ru.ggkit.ch.prof.vmc.dto.read.ProductReadDto;
import ru.ggkit.ch.prof.vmc.dto.update.InStockUpdateDto;
import ru.ggkit.ch.prof.vmc.dto.update.PriceUpdateDto;
import ru.ggkit.ch.prof.vmc.dto.update.ProductUpdateDto;
import ru.ggkit.ch.prof.vmc.entity.InStock;
import ru.ggkit.ch.prof.vmc.entity.Machine;
import ru.ggkit.ch.prof.vmc.entity.Price;
import ru.ggkit.ch.prof.vmc.entity.Product;
import ru.ggkit.ch.prof.vmc.mapper.ProductMapper;
import ru.ggkit.ch.prof.vmc.repository.InStockRepository;
import ru.ggkit.ch.prof.vmc.repository.MachineRepository;
import ru.ggkit.ch.prof.vmc.repository.PriceRepository;
import ru.ggkit.ch.prof.vmc.repository.ProductRepository;

@Service
@RequiredArgsConstructor
public class DefaultProductService implements ProductService {

  private final ProductRepository repProduct;

  private final MachineRepository repMachine;

  private final PriceRepository repPrice;

  private final InStockRepository repInStock;

  private final ProductMapper mapper;

  @Transactional
  @Override
  public Long createProduct(@NonNull ProductCreateDto request) {
    Product product = mapper.createToProduct(request);
    Price price = mapper.createToPrice(request.price());
    Product saved = repProduct.save(product);
    repPrice.save(price);
    return saved.getId();
  }

  @Transactional
  @Override
  public Long createInStock(@NonNull InStockCreateDto request) {
    Product product = repProduct.findById(request.productId())
        .orElseThrow(() -> new EntityNotFoundException("Product not found"));
    Machine machine = repMachine.findById(request.machineId())
        .orElseThrow(() -> new EntityNotFoundException("Machine not found"));
    InStock inStock = mapper.createToInStock(request);
    inStock.setProduct(product);
    inStock.setMachine(machine);
    product.addInStock(inStock);
    InStock saved = repInStock.save(inStock);
    return saved.getId();
  }

  @Transactional
  @Override
  public void changeInStock(@NonNull InStockUpdateDto request) {
    InStock inStock = repInStock.findById(request.id())
        .orElseThrow(() -> new EntityNotFoundException("product in stock information not found"));
    mapper.updateInStockFromDto(request, inStock);
  }

  @Transactional(readOnly = true)
  @Override
  public InStockReadDto findInStock(long id) {
    InStock inStock = repInStock.findFullInStock(id)
        .orElseThrow(() -> new EntityNotFoundException("Stock information not found"));
    return mapper.inStockToRead(inStock);
  }

  @Transactional
  @Override
  public void addPrice(@NonNull PriceUpdateDto request) {
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
    mapper.updateProductFromDto(request, product);
  }

  @Transactional
  @Override
  public void deleteProduct(long id) {
    repProduct.deleteById(id);
  }
}