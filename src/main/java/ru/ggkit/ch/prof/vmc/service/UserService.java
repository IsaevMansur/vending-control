package ru.ggkit.ch.prof.vmc.service;

import lombok.NonNull;
import org.springframework.transaction.annotation.Transactional;
import ru.ggkit.ch.prof.vmc.dto.create.RoleCreateDto;
import ru.ggkit.ch.prof.vmc.dto.create.UserCreateDto;
import ru.ggkit.ch.prof.vmc.dto.read.UserReadDto;
import ru.ggkit.ch.prof.vmc.dto.update.UserUpdateDto;

public interface UserService {

  @Transactional
  Long createUser(@NonNull UserCreateDto dto);

  @Transactional
  Long createRole(@NonNull RoleCreateDto dto);

  @Transactional(readOnly = true)
  UserReadDto findUser(long id);

  @Transactional
  void updateUser(@NonNull UserUpdateDto dto);

  @Transactional
  void deleteUser(long id);
}
