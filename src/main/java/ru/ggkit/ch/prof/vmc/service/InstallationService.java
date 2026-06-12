package ru.ggkit.ch.prof.vmc.service;

import java.util.Set;
import ru.ggkit.ch.prof.vmc.dto.create.InstallationCreateDto;
import ru.ggkit.ch.prof.vmc.dto.read.InstallationReadDto;

public interface InstallationService {

  InstallationReadDto saveInstallation(InstallationCreateDto installationCreateDto);

  InstallationReadDto findInstallation(long id);

  Set<InstallationReadDto> findInstallationsForMachine(long machineId);
}
