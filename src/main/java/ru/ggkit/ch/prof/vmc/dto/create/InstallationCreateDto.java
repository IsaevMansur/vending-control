package ru.ggkit.ch.prof.vmc.dto.create;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record InstallationCreateDto(
    @NotBlank
    String location,
    @NotNull
    LocalDate installedAt
) {

}
