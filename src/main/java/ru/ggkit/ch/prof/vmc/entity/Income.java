package ru.ggkit.ch.prof.vmc.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "incomes", schema = "vmc")
public class Income {

  @Id
  @GeneratedValue(
      generator = "income_id_seq",
      strategy = GenerationType.SEQUENCE
  )
  @SequenceGenerator(
      name = "income_id_seq",
      sequenceName = "income_id_seq"
  )
  private Long id;

  @Column(nullable = false)
  private BigDecimal value;

  @OneToOne(
      fetch = FetchType.LAZY,
      optional = false
  )
  private Installation installation;
}
