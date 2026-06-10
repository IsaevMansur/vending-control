package ru.ggkit.ch.prof.vmc.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.ggkit.ch.prof.vmc.entity.Sale;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {

  @Query("from Sale s "
      + "join fetch s.machine "
      + "join fetch s.paymentType "
      + "join fetch s.product "
      + "where s.id=:id")
  Optional<Sale> findFullSale(long id);
}