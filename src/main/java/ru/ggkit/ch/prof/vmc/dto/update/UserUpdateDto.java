package ru.ggkit.ch.prof.vmc.dto.update;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record UserUpdateDto(
    @Positive
    long id,
    @NotBlank
    String surname,
    @NotBlank
    String name,
    @NotBlank
    String patronymic,
    @NotNull
    @Valid
    ContactInfoUpdateDto contactInfo,
    @Positive
    long roleId
) {

}
