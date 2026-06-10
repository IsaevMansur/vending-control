package ru.ggkit.ch.prof.vmc.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ggkit.ch.prof.vmc.dto.create.RoleCreateDto;
import ru.ggkit.ch.prof.vmc.dto.create.UserCreateDto;
import ru.ggkit.ch.prof.vmc.dto.read.UserReadDto;
import ru.ggkit.ch.prof.vmc.dto.update.UserUpdateDto;
import ru.ggkit.ch.prof.vmc.entity.Role;
import ru.ggkit.ch.prof.vmc.entity.User;
import ru.ggkit.ch.prof.vmc.exception.EntityNotFoundException;
import ru.ggkit.ch.prof.vmc.exception.SubEntityNotFoundException;
import ru.ggkit.ch.prof.vmc.mapper.UserMapper;
import ru.ggkit.ch.prof.vmc.repository.RoleRepository;
import ru.ggkit.ch.prof.vmc.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class DefaultUserService implements UserService {

  private final UserRepository repUser;

  private final RoleRepository repRole;

  private final UserMapper mapper;

  @Transactional
  @Override
  public Long createUser(@NonNull UserCreateDto dto) {
    User toSave = mapper.createToUser(dto);
    Role role = repRole.findById(dto.roleId())
        .orElseThrow(() -> SubEntityNotFoundException.of(Role.class));
    toSave.setRole(role);
    User saved = repUser.save(toSave);
    return saved.getId();
  }

  @Transactional
  @Override
  public Long createRole(@NonNull RoleCreateDto dto) {
    Role toSave = mapper.createToRole(dto);
    Role save = repRole.save(toSave);
    return save.getId();
  }

  @Transactional(readOnly = true)
  @Override
  public UserReadDto findUser(long id) {
    User user = repUser.findById(id)
        .orElseThrow(() -> EntityNotFoundException.of(User.class, id));
    return mapper.userToRead(user);
  }

  @Transactional
  @Override
  public void updateUser(@NonNull UserUpdateDto dto) {
    User user = repUser.findById(dto.id())
        .orElseThrow(() -> EntityNotFoundException.of(User.class, dto.id()));
    mapper.updateFromDto(dto, user);
    if (!user.getRole().getId().equals(dto.roleId())) {
      Role role = repRole.findById(dto.roleId())
          .orElseThrow(() -> SubEntityNotFoundException.of(Role.class));
      user.setRole(role);
    }
  }

  @Transactional
  @Override
  public void deleteUser(long id) {
    repUser.deleteById(id);
  }
}