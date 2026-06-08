package ru.ggkit.ch.prof.vmc.dto.create;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.Set;

public record MachineCreateDto(
    @NotBlank
    String mark,
    @NotBlank
    String model,
    @Size(min = 1)
    Set<Long> paymentTypes
) {

}
