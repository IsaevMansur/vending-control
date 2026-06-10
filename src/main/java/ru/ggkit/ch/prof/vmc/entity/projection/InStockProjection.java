package ru.ggkit.ch.prof.vmc.entity.projection;

import ru.ggkit.ch.prof.vmc.entity.Machine;
import ru.ggkit.ch.prof.vmc.entity.Product;

public interface InStockProjection {

  Machine getMachine();

  Product getProduct();
}
