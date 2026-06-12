package ru.ggkit.ch.prof.vmc.dto.create;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.time.LocalDate;

public record InstallationCreateDto(
    @Positive
    long machineId,
    @NotBlank
    String location,
    @NotNull
    LocalDate installedAt
) {

}
