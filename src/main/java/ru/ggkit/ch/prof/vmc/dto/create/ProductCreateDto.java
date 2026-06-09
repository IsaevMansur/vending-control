package ru.ggkit.ch.prof.vmc.dto.create;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductCreateDto(
    @NotBlank
    String name,
    @NotBlank
    String description,
    @NotNull
    PriceCreateDto price
) {

}
