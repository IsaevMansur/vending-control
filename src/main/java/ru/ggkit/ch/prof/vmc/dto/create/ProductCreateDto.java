package ru.ggkit.ch.prof.vmc.dto.create;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;

public record ProductCreateDto(
    @NotBlank
    String name,
    @NotBlank
    String description,
    @NotNull
    @Positive
    BigDecimal price,
    @NotNull
    @Valid
    InStockCreateDto inStock
) {

}
