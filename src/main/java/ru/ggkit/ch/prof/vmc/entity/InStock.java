package ru.ggkit.ch.prof.vmc.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jspecify.annotations.Nullable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "in_stock", schema = "vmc")
public class InStock {

  @Id
  @GeneratedValue(
      generator = "in_stock_id_seq",
      strategy = GenerationType.SEQUENCE
  )
  @SequenceGenerator(
      name = "in_stock_id_seq",
      sequenceName = "in_stock_id_seq"
  )
  @Nullable
  private Long id;

  @ManyToOne(
      fetch = FetchType.LAZY,
      optional = false
  )
  @Nullable
  private Machine machine;

  @OneToOne(
      fetch = FetchType.LAZY,
      optional = false
  )
  @Nullable
  private Product product;

  @Column(nullable = false)
  private Integer stock;

  @Column(nullable = false)
  private Integer minStock;
}