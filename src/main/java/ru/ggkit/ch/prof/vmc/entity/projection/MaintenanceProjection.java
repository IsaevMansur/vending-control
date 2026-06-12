package ru.ggkit.ch.prof.vmc.entity.projection;

import ru.ggkit.ch.prof.vmc.entity.Machine;
import ru.ggkit.ch.prof.vmc.entity.User;

public interface MaintenanceProjection {

  Machine getMachine();
  User getUser();
}
