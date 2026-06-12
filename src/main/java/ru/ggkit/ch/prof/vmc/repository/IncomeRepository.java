package ru.ggkit.ch.prof.vmc.repository;

import jakarta.persistence.EntityNotFoundException;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.ggkit.ch.prof.vmc.entity.Income;

@Repository
public interface IncomeRepository extends JpaRepository<Income, Long> {

  default Income findIncome(long id) {
    return findById(id).orElseThrow(
        () -> new EntityNotFoundException("Income with ID=%d not found".formatted(id)));
  }

  @Query(
      "from Income i "
          + "join fetch i.installation ins "
          + "where ins.machine.id=:machineId"
  )
  Set<Income> findAllIncomeByMachineId(long machineId);
}
