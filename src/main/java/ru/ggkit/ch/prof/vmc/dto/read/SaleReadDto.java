package ru.ggkit.ch.prof.vmc.dto.read;

import java.time.LocalDateTime;

public record SaleReadDto(
    long id,
    long machineId,
    long productId,
    int soldCount,
    LocalDateTime soldAt,
    long paymentTypeId
) {

}
