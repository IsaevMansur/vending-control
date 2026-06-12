package ru.ggkit.ch.prof.vmc.dto.create;

import jakarta.validation.constraints.Positive;

public record InStockCreateDto(
    @Positive
    long machineId,
    @Positive
    long productId,
    @Positive
    int stock,
    @Positive
    int minStock
) {

}
