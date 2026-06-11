package ru.ggkit.ch.prof.vmc.dto.update;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record ContactInfoUpdateDto(
    @NotBlank
    @Email
    String email,
    @NotBlank
    @Length(min = 9, max = 9)
    String phone
) {

}
