package ru.ggkit.ch.prof.vmc.repository;

import jakarta.persistence.EntityNotFoundException;
import java.util.Optional;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.ggkit.ch.prof.vmc.entity.Maintenance;
import ru.ggkit.ch.prof.vmc.entity.projection.MaintenanceProjection;

@Repository
public interface MaintenanceRepository extends JpaRepository<Maintenance, Long> {

  @Query(
      "from Maintenance m "
          + "join fetch m.machine "
          + "join fetch m.user "
          + "where m.id=:id"
  )
  Optional<Maintenance> findFullMaintenance(long id);

  default Maintenance findMaintenanceOrThrow(long id) {
    return findFullMaintenance(id).orElseThrow(
        () -> new EntityNotFoundException("Maintenance with ID=%d not found".formatted(id)));
  }

  @Query(
      "from Maintenance m "
          + "where m.machine.id=:id"
  )
  Set<Maintenance> findAllForMachine(long id);

  @Query(
      "select m as machine, u as user "
          + "from Maintenance "
          + "join Machine m "
          + "join User u "
          + "where m.id=:machineId and u.id=:userId"
  )
  Optional<MaintenanceProjection> findMaintenanceRequiredProps(long machineId, long userId);

  default MaintenanceProjection findMaintenanceRequiredPropsOrThrow(long machineId,
      long userId) {
    return findMaintenanceRequiredProps(machineId, userId).orElseThrow(
        () -> new EntityNotFoundException(
            "Required properties for create a maintenance not found"));
  }
}
