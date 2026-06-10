package ru.ggkit.ch.prof.vmc.service;

import ru.ggkit.ch.prof.vmc.dto.create.UserCreateDto;
import ru.ggkit.ch.prof.vmc.dto.read.UserReadDto;
import ru.ggkit.ch.prof.vmc.dto.update.UserUpdateDto;

public interface UserService {

  Long createUser(UserCreateDto dto);


  UserReadDto findUser(long id);

  void updateUser(UserUpdateDto dto);

  void deleteUser(long id);
}
