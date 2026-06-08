package ru.ggkit.ch.prof.vmc.dto.read;

import java.time.LocalDate;

public record InstallationReadDto(
    long id,
    String location,
    LocalDate installedAt,
    LocalDate uninstalledAt
) {

}
