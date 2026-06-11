package ru.ggkit.ch.prof.vmc.dto.read;

import java.time.LocalDateTime;

public record SaleReadDto(
    long id,
    MachineReadDto machine,
    ProductReadDto product,
    int soldCount,
    LocalDateTime soldAt,
    PaymentTypeReadDto paymentType
) {

}
