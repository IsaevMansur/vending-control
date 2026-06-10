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
import jakarta.validation.constraints.DecimalMin;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "prices", schema = "vmc")
public class Price implements ru.ggkit.ch.prof.vmc.entity.Entity {

  @Id
  @GeneratedValue(
      generator = "price_id_seq",
      strategy = GenerationType.SEQUENCE
  )
  @SequenceGenerator(
      name = "price_id_seq",
      sequenceName = "price_id_seq"
  )
  private Long id;

  @Column(nullable = false)
  @DecimalMin("0.01")
  private BigDecimal value;

  @Column(nullable = false)
  private boolean isActive;

  @ManyToOne(
      fetch = FetchType.LAZY,
      optional = false
  )
  private Product product;
}