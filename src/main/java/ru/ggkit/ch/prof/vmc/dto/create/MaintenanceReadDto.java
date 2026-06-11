package ru.ggkit.ch.prof.vmc.dto.create;

import java.time.LocalDate;
import java.util.Set;
import ru.ggkit.ch.prof.vmc.dto.read.MachineReadDto;
import ru.ggkit.ch.prof.vmc.dto.read.UserReadDto;

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
