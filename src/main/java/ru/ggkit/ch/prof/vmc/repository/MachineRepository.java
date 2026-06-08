package ru.ggkit.ch.prof.vmc.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.ggkit.ch.prof.vmc.entity.Machine;

@Repository
public interface MachineRepository extends JpaRepository<Machine, Long> {

  @Query("from Machine m "
      + "join fetch m.paymentTypes pt "
      + "where m.id=:id")
  Optional<Machine> findMachineWithPaymentTypes(long id);
}