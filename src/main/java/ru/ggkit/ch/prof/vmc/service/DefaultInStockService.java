package ru.ggkit.ch.prof.vmc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ggkit.ch.prof.vmc.dto.create.InStockCreateDto;
import ru.ggkit.ch.prof.vmc.dto.read.InStockReadDto;
import ru.ggkit.ch.prof.vmc.dto.update.InStockUpdateDto;
import ru.ggkit.ch.prof.vmc.entity.InStock;
import ru.ggkit.ch.prof.vmc.entity.projection.InStockProjection;
import ru.ggkit.ch.prof.vmc.mapper.InStockMapper;
import ru.ggkit.ch.prof.vmc.repository.InStockRepository;

@Service
@RequiredArgsConstructor
public class DefaultInStockService implements InStockService {

  private final InStockRepository repoInStock;
  private final InStockMapper inStockMapper;

  @Override
  @Transactional
  public InStockReadDto saveInStock(InStockCreateDto inStockCreateDto) {
    InStockProjection projection = repoInStock.findRequiredPropsOrThrow(
        inStockCreateDto.machineId(), inStockCreateDto.productId());
    InStock inStock = inStockMapper.createDtoToInStock(inStockCreateDto);
    inStock.setMachine(projection.getMachine());
    inStock.setProduct(projection.getProduct());
    InStock saved = repoInStock.save(inStock);
    return inStockMapper.inStockToReadDto(saved);
  }

  @Override
  @Transactional
  public InStockReadDto findInStock(long id) {
    InStock inStock = repoInStock.findInStockOrThrow(id);
    return inStockMapper.inStockToReadDto(inStock);
  }

  @Override
  public void updateInStock(InStockUpdateDto inStockUpdateDto) {
    InStock inStock = repoInStock.findInStockOrThrow(inStockUpdateDto.id());
    inStockMapper.updateInStockFromDto(inStockUpdateDto, inStock);
  }
}