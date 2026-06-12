package ru.ggkit.ch.prof.vmc.repository;

import jakarta.persistence.EntityNotFoundException;
import java.util.Optional;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.ggkit.ch.prof.vmc.entity.Installation;

@Repository
public interface InstallationRepository extends JpaRepository<Installation, Long> {

  @Query(
      "from Installation i "
          + "where i.machine.id=:machineId"
  )
  Set<Installation> findAllInstallationByMachineId(long machineId);

  @Query(
      "from Installation i "
          + "join fetch i.machine "
          + "where i.id=:id"
  )
  Optional<Installation> findFullInstallation(long id);

  default Installation findFullInstallationOrThrow(long id) {
    return findFullInstallation(id).orElseThrow(
        () -> new EntityNotFoundException("Installation with ID=%d not found".formatted(id)));
  }

  default Installation findInstallation(long id) {
    return findById(id).orElseThrow(
        () -> new EntityNotFoundException("Installation with ID=%d not found".formatted(id)));
  }
}
