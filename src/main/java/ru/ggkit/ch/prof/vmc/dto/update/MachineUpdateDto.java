package ru.ggkit.ch.prof.vmc.dto.update;

import java.util.Set;

public record MachineUpdateDto(
    long id,
    String mark,
    String model,
    Set<Long> paymentTypeIds
) {

}
