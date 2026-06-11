package ru.ggkit.ch.prof.vmc.dto.read;

import java.math.BigDecimal;

public record ProductReadDto(
    long id,
    String name,
    String description,
    BigDecimal price,
    InStockReadDto inStock
) {

}
