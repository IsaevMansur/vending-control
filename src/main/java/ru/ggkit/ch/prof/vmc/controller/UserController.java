package ru.ggkit.ch.prof.vmc.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ggkit.ch.prof.vmc.service.UserService;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {

  private final UserService service;
}