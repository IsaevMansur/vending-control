package ru.ggkit.ch.prof.vmc.dto.read;

public record UserReadDto(
    long id,
    String surname,
    String name,
    String patronymic,
    ContactsReadDto contacts,
    RoleReadDto role
) {

}
