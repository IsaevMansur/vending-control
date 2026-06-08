package ru.ggkit.ch.prof.vmc.repository;

import java.util.Optional;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.ggkit.ch.prof.vmc.entity.Machine;
import ru.ggkit.ch.prof.vmc.entity.PaymentType;

@Repository
public interface MachineRepository extends JpaRepository<Machine, Long> {

  @Query("from PaymentType pt where pt.id in :ids")
  Set<PaymentType> findAllPaymentTypesByIds(Set<Long> ids);

  @Query("from Machine m "
      + "join fetch m.paymentTypes pt "
      + "where m.id=:id")
  Optional<Machine> findMachineWithPaymentTypes(long id);
}