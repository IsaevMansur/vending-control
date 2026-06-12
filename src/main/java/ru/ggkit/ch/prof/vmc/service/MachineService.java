package ru.ggkit.ch.prof.vmc.service;

import ru.ggkit.ch.prof.vmc.dto.create.MachineCreateDto;
import ru.ggkit.ch.prof.vmc.dto.read.MachineReadDto;
import ru.ggkit.ch.prof.vmc.dto.update.MachineUpdateDto;

public interface MachineService {

  MachineReadDto saveMachine(MachineCreateDto dto);

  MachineReadDto findMachine(long id);

  void updateMachine(MachineUpdateDto dto);

  void archiveMachine(long id);
}
