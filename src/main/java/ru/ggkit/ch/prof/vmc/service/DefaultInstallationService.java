package ru.ggkit.ch.prof.vmc.service;

import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ggkit.ch.prof.vmc.dto.create.InstallationCreateDto;
import ru.ggkit.ch.prof.vmc.dto.read.InstallationReadDto;
import ru.ggkit.ch.prof.vmc.entity.Installation;
import ru.ggkit.ch.prof.vmc.entity.Machine;
import ru.ggkit.ch.prof.vmc.mapper.InstallationMapper;
import ru.ggkit.ch.prof.vmc.repository.InstallationRepository;
import ru.ggkit.ch.prof.vmc.repository.MachineRepository;

@Service
@RequiredArgsConstructor
public class DefaultInstallationService implements InstallationService {

  private final InstallationRepository repoInstallation;
  private final MachineRepository repoMachine;
  private final InstallationMapper installationMapper;

  @Override
  @Transactional
  public InstallationReadDto saveInstallation(InstallationCreateDto installationCreateDto) {
    Machine machine = repoMachine
        .findMachineOrThrow(installationCreateDto.machineId());
    Installation installation = installationMapper
        .createDtoToInstallation(installationCreateDto);
    installation.setMachine(machine);
    Installation saved = repoInstallation.save(installation);
    return installationMapper.installationToReadDto(saved);
  }

  @Override
  public InstallationReadDto findInstallation(long id) {
    Installation installation = repoInstallation.findFullInstallationOrThrow(id);
    return installationMapper.installationToReadDto(installation);
  }

  @Override
  public Set<InstallationReadDto> findInstallationsForMachine(long machineId) {
    Set<Installation> founds = repoInstallation.findAllInstallationByMachineId(machineId);
    return founds.stream()
        .map(installationMapper::installationToReadDto)
        .collect(Collectors.toSet());
  }
}
