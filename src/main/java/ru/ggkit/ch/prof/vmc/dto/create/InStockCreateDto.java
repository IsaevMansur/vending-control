package ru.ggkit.ch.prof.vmc.dto.create;

import jakarta.validation.constraints.Positive;

public record InStockCreateDto(
    @Positive
    int stock,
    @Positive
    int minimumStock
) {

}
