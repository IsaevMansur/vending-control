package ru.ggkit.ch.prof.vmc.entity.projection;

import ru.ggkit.ch.prof.vmc.entity.Machine;
import ru.ggkit.ch.prof.vmc.entity.PaymentType;
import ru.ggkit.ch.prof.vmc.entity.Product;

public interface SaleProjection {
  Machine getMachine();
  Product getProduct();
  PaymentType getPaymentType();
}
