package ru.ggkit.ch.prof.vmc.entity;

import jakarta.persistence.CascadeType;
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
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

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
  @Nullable
  private Long id;

  @Column(nullable = false)
  @NonNull
  private String surname;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private String patronymic;

  @OneToOne(
      optional = false,
      orphanRemoval = true,
      fetch = FetchType.LAZY,
      cascade = CascadeType.ALL
  )
  @Nullable
  private ContactInfo contactInfo;

  @ManyToOne(
      fetch = FetchType.LAZY,
      optional = false
  )
  @Nullable
  private Role role;
}
