package ru.ggkit.ch.prof.vmc.repository;

import jakarta.persistence.EntityNotFoundException;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.ggkit.ch.prof.vmc.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  @Query(
      "from User u "
          + "join fetch u.role "
          + "where u.id=:id"
  )
  Optional<User> findUserWithRole(long id);

  default User findUserWithRoleOrThrow(long id) {
    return findUserWithRole(id).orElseThrow(
        () -> new EntityNotFoundException("User with ID=%d not found".formatted(id)));
  }

  default User findUserOrThrow(long id) {
    return findById(id).orElseThrow(
        () -> new EntityNotFoundException("User with ID=%d not found".formatted(id)));
  }
}
