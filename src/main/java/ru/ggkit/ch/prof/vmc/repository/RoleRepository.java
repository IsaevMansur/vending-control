package ru.ggkit.ch.prof.vmc.repository;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.ggkit.ch.prof.vmc.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

  default Role findRoleOrThrow(Long id) {
    return findById(id).orElseThrow(
        () -> new EntityNotFoundException("Role with ID=%d not found".formatted(id)));
  }

  @Query(
      "select exists "
          + "(select 1 from Role r "
          + "where r.name in ('ADMIN', 'SIMPLE_USER'))"
  )
  boolean existsRequiredRoles();
}