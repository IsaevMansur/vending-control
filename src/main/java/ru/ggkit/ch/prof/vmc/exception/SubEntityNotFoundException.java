package ru.ggkit.ch.prof.vmc.exception;

import org.jspecify.annotations.NonNull;
import ru.ggkit.ch.prof.vmc.entity.Entity;

public class SubEntityNotFoundException extends EntityNotFoundException {

  public static SubEntityNotFoundException of(@NonNull Class<? extends Entity> entity) {
    return new SubEntityNotFoundException("No suitable %s not found".formatted(entity.getName()));
  }

  public static SubEntityNotFoundException of(@NonNull Class<? extends Entity> entity, long id) {
    return new SubEntityNotFoundException(
        "No suitable %s by ID=%d not found".formatted(entity.getName(), id));
  }

  public SubEntityNotFoundException(String message) {
    super(message);
  }
}