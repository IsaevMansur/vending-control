package ru.ggkit.ch.prof.vmc.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.ggkit.ch.prof.vmc.dto.create.ContactsCreateDto;
import ru.ggkit.ch.prof.vmc.dto.create.RoleCreateDto;
import ru.ggkit.ch.prof.vmc.dto.create.UserCreateDto;
import ru.ggkit.ch.prof.vmc.dto.read.ContactsReadDto;
import ru.ggkit.ch.prof.vmc.dto.read.RoleReadDto;
import ru.ggkit.ch.prof.vmc.dto.read.UserReadDto;
import ru.ggkit.ch.prof.vmc.dto.update.ContactsUpdateDto;
import ru.ggkit.ch.prof.vmc.dto.update.UserUpdateDto;
import ru.ggkit.ch.prof.vmc.entity.Contacts;
import ru.ggkit.ch.prof.vmc.entity.Role;
import ru.ggkit.ch.prof.vmc.entity.User;

@Mapper
public interface UserMapper {

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "role", ignore = true)
  User createToUser(UserCreateDto dto);

  @Mapping(target = "id", ignore = true)
  Contacts createToContact(ContactsCreateDto dto);

  @Mapping(target = "id", ignore = true)
  Role createToRole(RoleCreateDto dto);

  UserReadDto userToRead(User user);

  RoleReadDto roleToRead(Role role);

  ContactsReadDto contactsToRead(Contacts contacts);

  @Mapping(target = "role", ignore = true)
  User updateToUser(UserUpdateDto dto);

  Contacts updateToContacts(ContactsUpdateDto dto);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "roleId", ignore = true)
  void updateFromDto(UserUpdateDto dto, @MappingTarget User user);
}