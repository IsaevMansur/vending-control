package ru.ggkit.ch.prof.vmc.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
  private Long id;

  @ManyToOne(
      fetch = FetchType.LAZY,
      optional = false
  )
  private Machine machine;

  @ManyToOne(
      fetch = FetchType.LAZY,
      optional = false
  )
  private Product product;

  @Column(nullable = false)
  @Min(0)
  private int stock;

  @Column(nullable = false)
  @Min(0)
  private int minimumStock;
}