package ru.ggkit.ch.prof.vmc.dto.create;

import jakarta.validation.constraints.NotBlank;

public record RoleCreateDto(@NotBlank String roleName) {

}
