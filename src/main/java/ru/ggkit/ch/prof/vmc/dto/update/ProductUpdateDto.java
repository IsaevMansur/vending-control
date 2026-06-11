package ru.ggkit.ch.prof.vmc.dto.update;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;

public record ProductUpdateDto(
    @Positive
    long id,
    @NotBlank
    String name,
    @NotBlank
    String description,
    @NotNull
    @Positive
    BigDecimal price,
    @NotNull
    @Valid
    InStockUpdateDto inStock
) {

}
