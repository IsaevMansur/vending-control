package ru.ggkit.ch.prof.vmc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ggkit.ch.prof.vmc.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

  default Role findRole(Long id) {
    return findById(id)
        .orElseThrow(() -> SubEntityNotFoundException.of(Role.class));
  }

}
