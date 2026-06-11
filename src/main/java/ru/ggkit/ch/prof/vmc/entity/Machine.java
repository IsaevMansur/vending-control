package ru.ggkit.ch.prof.vmc.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.util.Set;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jspecify.annotations.Nullable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "machines", schema = "vmc")
public class Machine {

  @Id
  @GeneratedValue(
      generator = "machine_id_seq",
      strategy = GenerationType.SEQUENCE
  )
  @SequenceGenerator(
      name = "machine_id_seq",
      sequenceName = "machine_id_seq"
  )
  @Nullable
  private Long id;

  @Column(nullable = false)
  private String mark;

  @Column(nullable = false)
  private String model;

  @ManyToMany
  @JoinTable(
      name = "machines_payment_types",
      joinColumns = @JoinColumn(name = "machine_id"),
      inverseJoinColumns = @JoinColumn(name = "payment_type_id")
  )
  private Set<PaymentType> paymentTypes;

  @OneToMany(
      cascade = CascadeType.ALL,
      mappedBy = "machine",
      orphanRemoval = true
  )
  private Set<Installation> installations;

  public void addInstallation(Installation ref) {
    installations.add(ref);
    ref.setMachine(this);
  }

  public void removeInstallation(Installation ref) {
    installations.remove(ref);
  }
}