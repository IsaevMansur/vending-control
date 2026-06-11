package ru.ggkit.ch.prof.vmc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ggkit.ch.prof.vmc.dto.create.ProductCreateDto;
import ru.ggkit.ch.prof.vmc.dto.read.ProductReadDto;
import ru.ggkit.ch.prof.vmc.dto.update.ProductUpdateDto;
import ru.ggkit.ch.prof.vmc.entity.Product;
import ru.ggkit.ch.prof.vmc.mapper.ProductMapper;
import ru.ggkit.ch.prof.vmc.repository.ProductRepository;

@Service
@RequiredArgsConstructor
public class DefaultProductService implements ProductService {

  private final ProductRepository repoProduct;
  private final ProductMapper productMapper;

  @Override
  @Transactional
  public ProductReadDto createProduct(ProductCreateDto request) {
    Product product = productMapper.createDtoToProduct(request);
    Product saved = repoProduct.save(product);
    return productMapper.productToReadDto(saved);
  }

  @Override
  @Transactional(readOnly = true)
  public ProductReadDto findProduct(long id) {
    Product product = repoProduct.findProduct(id);
    return productMapper.productToReadDto(product);
  }

  @Override
  @Transactional
  public void updateProduct(ProductUpdateDto productUpdateDto) {
    Product product = repoProduct.findFullProductThrow(productUpdateDto.id());
    productMapper.updateProductFromDto(productUpdateDto, product);
  }

  @Override
  @Transactional
  public void deleteProduct(long id) {
    repoProduct.deleteById(id);
  }
}