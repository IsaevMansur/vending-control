package ru.ggkit.ch.prof.vmc.dto.create;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public record PriceCreateDto(
    @DecimalMin("0.01")
    @NotNull
    BigDecimal value
) {

}
