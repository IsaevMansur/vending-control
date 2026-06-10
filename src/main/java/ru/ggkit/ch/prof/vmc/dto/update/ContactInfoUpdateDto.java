package ru.ggkit.ch.prof.vmc.dto.update;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import org.hibernate.validator.constraints.Length;

public record ContactInfoUpdateDto(
    @Positive
    long id,
    @NotBlank
    @Email
    String email,
    @NotBlank
    @Length(min = 9, max = 9)
    String phone
) {

}
