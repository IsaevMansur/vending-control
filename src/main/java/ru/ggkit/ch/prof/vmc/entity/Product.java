package ru.ggkit.ch.prof.vmc.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jspecify.annotations.Nullable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(
    name = "products",
    schema = "vmc"
)
public class Product {

  @Id
  @GeneratedValue(
      generator = "product_id_seq",
      strategy = GenerationType.SEQUENCE
  )
  @SequenceGenerator(
      name = "product_id_seq",
      sequenceName = "product_id_seq"
  )
  @Nullable
  private Long id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private String description;

  @Column(nullable = false)
  private BigDecimal price;

  @OneToOne(
      cascade = CascadeType.ALL,
      mappedBy = "product",
      orphanRemoval = true
  )
  @Nullable
  private InStock inStock;
}