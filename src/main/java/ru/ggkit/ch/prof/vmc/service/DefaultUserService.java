package ru.ggkit.ch.prof.vmc.service;

import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ggkit.ch.prof.vmc.dto.create.UserCreateDto;
import ru.ggkit.ch.prof.vmc.dto.read.UserReadDto;
import ru.ggkit.ch.prof.vmc.dto.update.UserUpdateDto;
import ru.ggkit.ch.prof.vmc.entity.Role;
import ru.ggkit.ch.prof.vmc.entity.User;
import ru.ggkit.ch.prof.vmc.mapper.UserMapper;
import ru.ggkit.ch.prof.vmc.repository.RoleRepository;
import ru.ggkit.ch.prof.vmc.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class DefaultUserService implements UserService {

  private final UserRepository repoUser;
  private final RoleRepository repoRole;
  private final UserMapper userMapper;

  @Override
  @Transactional
  public UserReadDto saveUser(UserCreateDto dto) {
    User toSave = userMapper.createToUser(dto);
    Role role = repoRole.findRoleOrThrow(dto.roleId());
    toSave.setRole(role);
    User saved = repoUser.save(toSave);
    return userMapper.userToReadDto(saved);
  }

  @Override
  @Transactional(readOnly = true)
  public UserReadDto findUser(long id) {
    User user = repoUser.findUserOrThrow(id);
    return userMapper.userToReadDto(user);
  }

  @Override
  @Transactional
  public void updateUser(UserUpdateDto userUpdateDto) {
    User user = repoUser.findUserWithRoleOrThrow(userUpdateDto.id());
    userMapper.updateFromDto(userUpdateDto, user);
    Role actualRole = user.getRole();
    if (Objects.nonNull(actualRole) && !Objects.equals(actualRole.getId(),
        userUpdateDto.roleId())) {
      Role role = repoRole.findRoleOrThrow(userUpdateDto.roleId());
      user.setRole(role);
    }
  }

  @Override
  @Transactional
  public void deleteUser(long id) {
    repoUser.deleteById(id);
  }
}