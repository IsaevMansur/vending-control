package ru.ggkit.ch.prof.vmc.dto.read;

public record UserReadDto(
    long id,
    String surname,
    String name,
    String patronymic,
    ContactInfoReadDto contactInfo,
    RoleReadDto role
) {

}
