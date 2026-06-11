package ru.ggkit.ch.prof.vmc.repository;

import jakarta.persistence.EntityNotFoundException;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.ggkit.ch.prof.vmc.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

  @Query(
      "from Product p "
          + "join fetch p.inStock "
          + "where p.id=:id"
  )
  Optional<Product> findFullProduct(long id);

  default Product findFullProductThrow(long id) {
    return findFullProduct(id).orElseThrow(
        () -> new EntityNotFoundException("Product with ID=%s not found".formatted(id)));
  }

  default Product findProduct(long id) {
    return findById(id).orElseThrow(
        () -> new EntityNotFoundException("Product with ID=%s not found".formatted(id)));
  }
}
