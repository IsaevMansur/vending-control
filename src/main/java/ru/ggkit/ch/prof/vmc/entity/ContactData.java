package ru.ggkit.ch.prof.vmc.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "contact_data", schema = "vmc")
public class ContactData {

  @Id
  @GeneratedValue(
      generator = "contact_data_id_seq",
      strategy = GenerationType.SEQUENCE
  )
  @SequenceGenerator(
      name = "contact_data_id_seq",
      sequenceName = "contact_data_id_seq"
  )
  private Long id;

  @OneToOne(
      fetch = FetchType.LAZY,
      optional = false
  )
  private User user;
}