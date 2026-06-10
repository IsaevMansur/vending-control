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
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "sales", schema = "vmc")
public class Sale implements ru.ggkit.ch.prof.vmc.entity.Entity {

  @Id
  @GeneratedValue(
      generator = "sale_id_seq",
      strategy = GenerationType.SEQUENCE
  )
  @SequenceGenerator(
      name = "sale_id_seq",
      sequenceName = "sale_id_seq"
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
  @Min(1)
  private int soldCount;

  @Column(nullable = false)
  private LocalDateTime soldAt;

  @ManyToOne(
      fetch = FetchType.LAZY,
      optional = false
  )
  private PaymentType paymentType;
}