package ru.ggkit.ch.prof.vmc.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.util.Set;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "payment_types", schema = "vmc")
public class PaymentType {

  @Id
  @GeneratedValue(
      generator = "payment_type_id_seq",
      strategy = GenerationType.SEQUENCE
  )
  @SequenceGenerator(
      name = "payment_type_id_seq",
      sequenceName = "payment_type_id_seq"
  )
  private Long id;

  @Column(
      nullable = false,
      unique = true
  )
  private String name;

  @ManyToMany(mappedBy = "paymentTypes")
  private Set<Machine> machines;

  @OneToMany(mappedBy = "paymentType")
  private Set<Sale> sales;
}