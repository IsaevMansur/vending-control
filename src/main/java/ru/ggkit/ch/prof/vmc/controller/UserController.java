package ru.ggkit.ch.prof.vmc.controller;

import jakarta.validation.Valid;
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
import ru.ggkit.ch.prof.vmc.dto.create.UserCreateDto;
import ru.ggkit.ch.prof.vmc.dto.read.UserReadDto;
import ru.ggkit.ch.prof.vmc.dto.update.UserUpdateDto;
import ru.ggkit.ch.prof.vmc.service.UserService;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @PostMapping
  public ResponseEntity<UserReadDto> createUser(
      @RequestBody @Valid UserCreateDto userCreateDto) {
    var saved = userService.createUser(userCreateDto);
    return ResponseEntity.ok(saved);
  }

  @GetMapping("{id}")
  public ResponseEntity<UserReadDto> findUser(@PathVariable long id) {
    UserReadDto user = userService.findUser(id);
    return ResponseEntity.ok(user);
  }

  @PutMapping
  public ResponseEntity<Void> updateUser(@RequestBody @Valid UserUpdateDto userUpdateDto) {
    userService.updateUser(userUpdateDto);
    return ResponseEntity.ok().build();
  }

  @DeleteMapping("{id}")
  public ResponseEntity<Void> deleteUser(@PathVariable long id) {
    userService.deleteUser(id);
    return ResponseEntity.ok().build();
  }
}