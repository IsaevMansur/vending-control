package ru.ggkit.ch.prof.vmc.repository;

import jakarta.persistence.EntityNotFoundException;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.ggkit.ch.prof.vmc.entity.InStock;
import ru.ggkit.ch.prof.vmc.entity.projection.InStockProjection;

@Repository
public interface InStockRepository extends JpaRepository<InStock, Long> {

  @Query(
      "select m as machine, p as product "
          + "from InStock "
          + "join Machine m "
          + "join Product p "
          + "where m.id=:machineId and p.id=:productId"
  )
  Optional<InStockProjection> findRequiredProps(long machineId, long productId);

  default InStockProjection findRequiredPropsOrThrow(long machineId, long productId) {
    return findRequiredProps(machineId, productId).orElseThrow(
        () -> new EntityNotFoundException("Required properties for in stock not found"));
  }

  @Query(
      "from InStock is "
          + "join fetch is.machine "
          + "join fetch is.product "
          + "where is.id=:id"
  )
  Optional<InStock> findInStock(long id);

  default InStock findInStockOrThrow(long id) {
    return findInStock(id).orElseThrow(() -> new EntityNotFoundException(
        "In stock information with ID=%d not found".formatted(id)));
  }
}
