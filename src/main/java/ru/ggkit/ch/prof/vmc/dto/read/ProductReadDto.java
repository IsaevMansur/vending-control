package ru.ggkit.ch.prof.vmc.dto.read;

public record ProductReadDto(
    long id,
    String name,
    String description,
    PriceReadDto price
) {

}
