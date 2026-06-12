package ru.ggkit.ch.prof.vmc.dto.create;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;

public record IncomeCreateDto(
    @Positive
    @NotNull
    BigDecimal value,
    @Positive
    long installationId
) {

}
