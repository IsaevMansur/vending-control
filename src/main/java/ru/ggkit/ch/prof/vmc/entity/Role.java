package ru.ggkit.ch.prof.vmc.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "roles", schema = "vmc")
public class Role {

  @Id
  @GeneratedValue(
      generator = "role_id_seq",
      strategy = GenerationType.SEQUENCE
  )
  @SequenceGenerator(
      name = "role_id_seq",
      sequenceName = "role_id_seq"
  )
  private Long id;

  @Column(
      nullable = false,
      unique = true
  )
  private String name;

  @OneToMany(mappedBy = "role")
  private Set<User> users;
}
