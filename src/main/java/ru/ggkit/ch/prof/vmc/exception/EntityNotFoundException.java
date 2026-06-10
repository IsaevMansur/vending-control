package ru.ggkit.ch.prof.vmc.exception;

import ru.ggkit.ch.prof.vmc.entity.Entity;

public class EntityNotFoundException extends RuntimeException {

  public static EntityNotFoundException of(Class<? extends Entity> entity, long id) {
    return new EntityNotFoundException("%s with ID=%d not found".formatted(entity.getName(), id));
  }

  public EntityNotFoundException(String message) {
    super(message);
  }
}