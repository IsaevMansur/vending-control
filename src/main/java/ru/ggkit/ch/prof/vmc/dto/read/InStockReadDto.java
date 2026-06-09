package ru.ggkit.ch.prof.vmc.dto.read;

public record InStockReadDto(
    long id,
    long machineId,
    long productId,
    int stock,
    int minimumStock
) {

}
