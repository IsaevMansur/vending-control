package ru.ggkit.ch.prof.vmc.dto.update;

import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;

public record IncomeUpdateDto(
    @Positive
    long id,
    @Positive
    BigDecimal value
) {

}
