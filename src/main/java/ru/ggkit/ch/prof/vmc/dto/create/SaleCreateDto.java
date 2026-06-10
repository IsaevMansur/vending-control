package ru.ggkit.ch.prof.vmc.dto.create;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.time.LocalDateTime;

public record SaleCreateDto(
    @Positive
    long machineId,
    @Positive
    long productId,
    @Positive
    int soldCount,
    @NotNull
    LocalDateTime soldAt,
    @Positive
    long paymentTypeId
) {

}
