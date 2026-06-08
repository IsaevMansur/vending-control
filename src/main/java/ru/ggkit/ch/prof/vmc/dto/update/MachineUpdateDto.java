package ru.ggkit.ch.prof.vmc.dto.update;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.util.Set;

public record MachineUpdateDto(
    @Positive
    long id,
    @NotBlank
    String mark,
    @NotBlank
    String model,
    @Size(min = 1)
    @NotNull
    Set<Long> paymentTypeIds
) {

}