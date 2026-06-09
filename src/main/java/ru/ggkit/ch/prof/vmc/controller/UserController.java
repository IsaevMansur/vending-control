package ru.ggkit.ch.prof.vmc.controller;

import jakarta.validation.Valid;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import ru.ggkit.ch.prof.vmc.dto.create.RoleCreateDto;
import ru.ggkit.ch.prof.vmc.dto.create.UserCreateDto;
import ru.ggkit.ch.prof.vmc.dto.read.UserReadDto;
import ru.ggkit.ch.prof.vmc.dto.update.UserUpdateDto;
import ru.ggkit.ch.prof.vmc.service.UserService;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {

  private final UserService service;

  @PostMapping
  public ResponseEntity<Long> createUser(@RequestBody @Valid UserCreateDto userCreateDto,
      UriComponentsBuilder builder) {
    Long savedId = service.createUser(userCreateDto);
    URI uri = builder.path("{id}").buildAndExpand(savedId).toUri();
    return ResponseEntity.created(uri).body(savedId);
  }

  @PostMapping("role")
  public ResponseEntity<Long> createRole(@RequestBody @Valid RoleCreateDto roleCreateDto,
      UriComponentsBuilder builder) {
    Long savedId = service.createRole(roleCreateDto);
    URI uri = builder.path("{id}").buildAndExpand(savedId).toUri();
    return ResponseEntity.created(uri).body(savedId);
  }

  @GetMapping("{id}")
  public ResponseEntity<UserReadDto> findUser(@PathVariable long id) {
    UserReadDto user = service.findUser(id);
    return ResponseEntity.ok(user);
  }

  @PutMapping
  public ResponseEntity<Void> updateUser(@RequestBody @Valid UserUpdateDto userUpdateDto) {
    service.updateUser(userUpdateDto);
    return ResponseEntity.ok().build();
  }

  @DeleteMapping("{id}")
  public ResponseEntity<Void> deleteUser(@PathVariable long id) {
    service.deleteUser(id);
    return ResponseEntity.ok().build();
  }
}