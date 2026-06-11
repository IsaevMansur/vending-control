package ru.ggkit.ch.prof.vmc.dto.read;

public record InStockReadDto(
    long id,
    int stock,
    int minimumStock
) {

}
