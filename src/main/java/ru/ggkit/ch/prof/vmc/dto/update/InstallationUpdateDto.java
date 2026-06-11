package ru.ggkit.ch.prof.vmc.dto.update;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.time.LocalDate;

public record InstallationUpdateDto(
    @Positive
    long id,
    @NotBlank
    String location,
    @NotNull
    LocalDate installedAt
) {

}
