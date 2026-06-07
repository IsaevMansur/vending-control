package ru.ggkit.ch.prof.vmc.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
@Table(name = "users", schema = "vmc")
public class User {

  @Id
  @GeneratedValue(
      generator = "user_id_seq",
      strategy = GenerationType.SEQUENCE
  )
  @SequenceGenerator(
      name = "user_id_seq",
      sequenceName = "user_id_seq"
  )
  private Long id;

  @OneToMany(
      cascade = CascadeType.ALL,
      mappedBy = "user",
      orphanRemoval = true
  )
  private Set<Maintenance> maintenances;

  @Column(nullable = false)
  private String surname;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private String patronymic;

  @OneToOne(
      optional = false,
      mappedBy = "user"
  )
  private ContactData contactData;

  @ManyToOne(
      fetch = FetchType.LAZY,
      optional = false
  )
  private Role role;

  public void addMaintenance(Maintenance ref) {
    maintenances.add(ref);
    ref.setUser(this);
  }

  public void removeMaintenance(Maintenance ref) {
    maintenances.remove(ref);
  }
}
