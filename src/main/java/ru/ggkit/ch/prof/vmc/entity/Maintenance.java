package ru.ggkit.ch.prof.vmc.entity;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.Set;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "maintenances", schema = "vmc")
public class Maintenance {

  @Id
  @GeneratedValue(
      generator = "maintenance_id_seq",
      strategy = GenerationType.SEQUENCE
  )
  @SequenceGenerator(
      name = "maintenance_id_seq",
      sequenceName = "maintenance_id_seq"
  )
  private Long id;

  @Column(nullable = false)
  private String status;

  @Column(nullable = false)
  private LocalDate lastMaintenanceDate;

  @Column(nullable = false)
  private String description;

  @Column(nullable = false)
  @ElementCollection
  private Set<String> problems;

  @ManyToOne(
      optional = false,
      fetch = FetchType.LAZY
  )
  private User user;
}