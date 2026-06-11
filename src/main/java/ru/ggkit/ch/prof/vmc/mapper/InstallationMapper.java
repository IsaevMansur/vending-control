package ru.ggkit.ch.prof.vmc.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.ggkit.ch.prof.vmc.dto.create.InstallationCreateDto;
import ru.ggkit.ch.prof.vmc.dto.read.InstallationReadDto;
import ru.ggkit.ch.prof.vmc.dto.update.InstallationUpdateDto;
import ru.ggkit.ch.prof.vmc.entity.Installation;

@Mapper(componentModel = "spring")
public interface InstallationMapper {

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "machine", ignore = true)
  Installation createDtoToInstallation(InstallationCreateDto installationCreateDto);

  @Mapping(target = "machine", ignore = true)
  Installation updateDtoToInstallation(InstallationUpdateDto installationUpdateDto);

  InstallationReadDto installationToReadDto(Installation installation);
}