package ru.ggkit.ch.prof.vmc.dto.read;

import java.math.BigDecimal;

public record PriceReadDto(
    long id,
    BigDecimal value
) {

}
