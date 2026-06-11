package ru.ggkit.ch.prof.vmc.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jspecify.annotations.Nullable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "contact_infos", schema = "vmc")
public class ContactInfo {

  @Id
  @GeneratedValue(
      generator = "contact_data_id_seq",
      strategy = GenerationType.SEQUENCE
  )
  @SequenceGenerator(
      name = "contact_data_id_seq",
      sequenceName = "contact_data_id_seq"
  )
  @Nullable
  private Long id;

  @Column(
      nullable = false,
      unique = true
  )
  private String email;

  @Column(
      nullable = false,
      unique = true
  )
  private String phone;
}