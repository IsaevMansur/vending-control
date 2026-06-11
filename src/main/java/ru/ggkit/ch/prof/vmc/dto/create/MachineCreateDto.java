package ru.ggkit.ch.prof.vmc.dto.create;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.Set;

public record MachineCreateDto(
    @NotBlank
    String mark,
    @NotBlank
    String model,
    @Size(min = 1)
    @NotNull
    Set<Long> paymentTypes,
    @NotNull
    InstallationCreateDto installation,
    @NotNull
    IncomeCreateDto income
) {

}
