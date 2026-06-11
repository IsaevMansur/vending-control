package ru.ggkit.ch.prof.vmc.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.ggkit.ch.prof.vmc.dto.create.ContactInfoCreateDto;
import ru.ggkit.ch.prof.vmc.dto.create.RoleCreateDto;
import ru.ggkit.ch.prof.vmc.dto.create.UserCreateDto;
import ru.ggkit.ch.prof.vmc.dto.read.ContactInfoReadDto;
import ru.ggkit.ch.prof.vmc.dto.read.RoleReadDto;
import ru.ggkit.ch.prof.vmc.dto.read.UserReadDto;
import ru.ggkit.ch.prof.vmc.dto.update.ContactInfoUpdateDto;
import ru.ggkit.ch.prof.vmc.dto.update.UserUpdateDto;
import ru.ggkit.ch.prof.vmc.entity.ContactInfo;
import ru.ggkit.ch.prof.vmc.entity.Role;
import ru.ggkit.ch.prof.vmc.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "role", ignore = true)
  User createToUser(UserCreateDto dto);

  @Mapping(target = "id", ignore = true)
  ContactInfo createToContact(ContactInfoCreateDto dto);

  @Mapping(target = "id", ignore = true)
  Role createToRole(RoleCreateDto dto);

  UserReadDto userToReadDto(User user);

  RoleReadDto roleToRead(Role role);

  ContactInfoReadDto contactsToRead(ContactInfo contacts);

  @Mapping(target = "role", ignore = true)
  User updateToUser(UserUpdateDto dto);

  ContactInfo updateToContacts(ContactInfoUpdateDto dto);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "role", ignore = true)
  void updateFromDto(UserUpdateDto dto, @MappingTarget User user);
}