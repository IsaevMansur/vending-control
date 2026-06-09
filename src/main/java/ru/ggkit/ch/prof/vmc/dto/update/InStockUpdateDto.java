package ru.ggkit.ch.prof.vmc.dto.update;

import jakarta.validation.constraints.Positive;

public record InStockUpdateDto(
    @Positive
    long id,
    @Positive
    int stock,
    @Positive
    int minimumStock
) {

}
