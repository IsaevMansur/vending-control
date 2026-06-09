package ru.ggkit.ch.prof.vmc.dto.update;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;

public record PriceUpdateDto(
    @Positive
    long id,
    @Positive
    long productId,
    @DecimalMin("0.01")
    @NotNull
    BigDecimal value
) {

}
