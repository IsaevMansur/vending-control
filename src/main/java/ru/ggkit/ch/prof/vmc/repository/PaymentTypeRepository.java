package ru.ggkit.ch.prof.vmc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.ggkit.ch.prof.vmc.entity.PaymentType;

@Repository
public interface PaymentTypeRepository extends JpaRepository<PaymentType, Long> {

  @Query(
      "select exists "
          + "(select 1 from PaymentType pt "
          + "where pt.name in ('CASH', 'QR', 'CARD'))"
  )
  boolean existsRequiredTypes();
}
