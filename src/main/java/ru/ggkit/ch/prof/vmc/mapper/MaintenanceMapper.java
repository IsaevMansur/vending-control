package ru.ggkit.ch.prof.vmc.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.ggkit.ch.prof.vmc.dto.create.MaintenanceCreateDto;
import ru.ggkit.ch.prof.vmc.dto.create.MaintenanceReadDto;
import ru.ggkit.ch.prof.vmc.entity.Maintenance;

@Mapper(
    componentModel = "spring",
    uses = {
        MachineMapper.class,
        ProductMapper.class
    }
)
public interface MaintenanceMapper {

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "machine", ignore = true)
  @Mapping(target = "user", ignore = true)
  Maintenance createDtoToMaintenance(MaintenanceCreateDto maintenanceCreateDto);

  MaintenanceReadDto maintenanceToReadDto(Maintenance maintenance);
}
