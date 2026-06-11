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

  @Query("from InStock is "
      + "join fetch is.machine "
      + "join fetch is.product "
      + "where is.id=:id")
  Optional<InStock> findFullInStock(long id);

  @Query("select m as machine, p as product "
      + "from Machine m "
      + "join Product p "
      + "where m.id=:machineId and p.id=:productId")
  Optional<InStockProjection> findInStockRequiredProps(long machineId, long productId);

  default InStock findFullInStockOrThrow(long id) {
    return findFullInStock(id).orElseThrow(
        () -> new EntityNotFoundException("In stock information not found"));
  }

  default InStock findInStockOrThrow(long id) {
    return findById(id).orElseThrow(() -> new EntityNotFoundException("In stock information not found"));
  }
}
