package ru.ggkit.ch.prof.vmc.mapper;

import org.mapstruct.Mapper;
import ru.ggkit.ch.prof.vmc.dto.read.PaymentTypeReadDto;
import ru.ggkit.ch.prof.vmc.entity.PaymentType;

@Mapper(componentModel = "spring")
public interface PaymentTypeMapper {

  PaymentTypeReadDto paymentTypeToReadDto(PaymentType paymentType);
}
