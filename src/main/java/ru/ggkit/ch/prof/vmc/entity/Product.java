package ru.ggkit.ch.prof.vmc.entity;

import jakarta.persistence.CascadeType;
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
import org.jspecify.annotations.Nullable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "products", schema = "vmc")
public class Product {

  @Id
  @GeneratedValue(
      generator = "product_id_seq",
      strategy = GenerationType.SEQUENCE
  )
  @SequenceGenerator(
      name = "product_id_seq",
      sequenceName = "product_id_seq"
  )
  @Nullable
  private Long id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private String description;

  @OneToMany(
      cascade = CascadeType.ALL,
      mappedBy = "product",
      orphanRemoval = true
  )
  private Set<Price> prices;

  @OneToMany(
      cascade = CascadeType.ALL,
      mappedBy = "product",
      orphanRemoval = true
  )
  private Set<InStock> inStocks;

  public void addInStock(InStock ref) {
    inStocks.add(ref);
    ref.setProduct(this);
  }

  public void addPrice(Price ref) {
    prices.add(ref);
    ref.setProduct(this);
  }
}