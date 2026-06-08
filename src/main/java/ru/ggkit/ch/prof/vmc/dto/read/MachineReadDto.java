package ru.ggkit.ch.prof.vmc.dto.read;

import java.util.Set;

public record MachineReadDto(
    long id,
    String mark,
    String model,
    Set<String> paymentTypes,
    InstallationReadDto installation,
    IncomeReadDto income
) {

}
