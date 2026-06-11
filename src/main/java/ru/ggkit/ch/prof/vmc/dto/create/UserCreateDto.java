package ru.ggkit.ch.prof.vmc.dto.create;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record UserCreateDto(
    @NotBlank
    String surname,
    @NotBlank
    String name,
    @NotBlank
    String patronymic,
    @NotNull
    @Valid
    ContactInfoCreateDto contactData,
    @Positive
    long roleId
) {

}
