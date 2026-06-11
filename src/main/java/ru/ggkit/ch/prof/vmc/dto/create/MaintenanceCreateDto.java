package ru.ggkit.ch.prof.vmc.dto.create;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.time.LocalDate;
import java.util.Set;

public record MaintenanceCreateDto(
    @NotBlank
    String status,
    @NotNull
    LocalDate lastMaintenanceDate,
    @NotBlank
    String description,
    @NotNull
    Set<String> problems,
    @Positive
    long machineId,
    @Positive
    long userId
) {

}
