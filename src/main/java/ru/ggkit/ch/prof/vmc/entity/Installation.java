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
import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jspecify.annotations.Nullable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "installations", schema = "vmc")
public class Installation {

  @Id
  @GeneratedValue(
      generator = "installation_id_seq",
      strategy = GenerationType.SEQUENCE
  )
  @SequenceGenerator(
      name = "installation_id_seq",
      sequenceName = "installation_id_seq"
  )
  @Nullable
  private Long id;

  @ManyToOne(
      optional = false,
      fetch = FetchType.LAZY
  )
  @Nullable
  private Machine machine;

  @Column(nullable = false)
  private String location;

  @Column(nullable = false)
  private LocalDate installedAt;

  @Column(nullable = false)
  private boolean isActive;

  @OneToOne(
      optional = false,
      mappedBy = "installation"
  )
  @Nullable
  private Income income;
}