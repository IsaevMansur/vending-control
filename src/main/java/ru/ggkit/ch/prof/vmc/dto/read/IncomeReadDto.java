package ru.ggkit.ch.prof.vmc.dto.read;

import java.math.BigDecimal;

public record IncomeReadDto(
    long id,
    long installationId,
    BigDecimal value
) {

}
