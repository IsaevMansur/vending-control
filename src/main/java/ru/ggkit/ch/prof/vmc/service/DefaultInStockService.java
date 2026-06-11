package ru.ggkit.ch.prof.vmc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ggkit.ch.prof.vmc.dto.create.InStockCreateDto;
import ru.ggkit.ch.prof.vmc.dto.read.InStockReadDto;
import ru.ggkit.ch.prof.vmc.dto.update.InStockUpdateDto;
import ru.ggkit.ch.prof.vmc.entity.InStock;
import ru.ggkit.ch.prof.vmc.mapper.InStockMapper;
import ru.ggkit.ch.prof.vmc.repository.InStockRepository;

@Service
@RequiredArgsConstructor
public class DefaultInStockService implements InStockService {

  private final InStockRepository repoInStock;
  private final InStockMapper inStockMapper;

  @Override
  @Transactional
  public InStockReadDto createInStock(InStockCreateDto inStockCreateDto) {
    InStock inStock = inStockMapper.createDtoToInStock(inStockCreateDto);
    InStock saved = repoInStock.save(inStock);
    return inStockMapper.inStockToReadDto(saved);
  }

  @Override
  @Transactional(readOnly = true)
  public InStockReadDto findInStock(long id) {
    InStock inStock = repoInStock.findFullInStockOrThrow(id);
    return inStockMapper.inStockToReadDto(inStock);
  }

  @Override
  @Transactional
  public void updateInStock(InStockUpdateDto inStockUpdateDto) {
    InStock inStock = repoInStock.findInStockOrThrow(inStockUpdateDto.id());
    inStockMapper.updateInStockFromDto(inStockUpdateDto, inStock);
  }

  @Override
  @Transactional
  public void deleteInStock(long id) {
    repoInStock.deleteById(id);
  }
}
