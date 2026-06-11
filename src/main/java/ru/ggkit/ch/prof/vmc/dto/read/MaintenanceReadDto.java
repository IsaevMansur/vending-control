package ru.ggkit.ch.prof.vmc.dto.read;

import java.time.LocalDate;
import java.util.Set;

public record MaintenanceReadDto(
    long id,
    String status,
    LocalDate lastMaintenanceDate,
    String description,
    Set<String> problems,
    MachineReadDto machine,
    UserReadDto user
) {

}
