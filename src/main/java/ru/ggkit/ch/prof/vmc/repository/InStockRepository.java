package ru.ggkit.ch.prof.vmc.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.ggkit.ch.prof.vmc.entity.InStock;

@Repository
public interface InStockRepository extends JpaRepository<InStock, Long> {

  @Query("from InStock is "
      + "join fetch is.machine "
      + "join fetch is.product "
      + "where is.id=:id")
  Optional<InStock> findFullInStock(long id);
}
