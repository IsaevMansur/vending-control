package ru.ggkit.ch.prof.vmc;

import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.ggkit.ch.prof.vmc.entity.PaymentType;
import ru.ggkit.ch.prof.vmc.entity.Role;
import ru.ggkit.ch.prof.vmc.repository.PaymentTypeRepository;
import ru.ggkit.ch.prof.vmc.repository.RoleRepository;

@Component
@RequiredArgsConstructor
public class DataInitializer implements ApplicationRunner {

  private final RoleRepository repoRole;
  private final PaymentTypeRepository repoPaymentType;

  @Override
  @Transactional
  public void run(@NonNull ApplicationArguments args) {
    if (!repoRole.existsRequiredRoles()) {
      Set<Role> requiredRoles = getRequiredRoles();
      repoRole.saveAll(requiredRoles);
    }
    if (!repoPaymentType.existsRequiredTypes()) {
      Set<PaymentType> requiredTypes = getRequiredPaymentTypes();
      repoPaymentType.saveAll(requiredTypes);
    }
  }

  private Set<Role> getRequiredRoles() {
    var admin = new Role();
    var simpleUser = new Role();
    admin.setName("ADMIN");
    simpleUser.setName("SIMPLE_USER");
    return Set.of(admin, simpleUser);
  }

  private Set<PaymentType> getRequiredPaymentTypes() {
    return Set.of();
  }
}
