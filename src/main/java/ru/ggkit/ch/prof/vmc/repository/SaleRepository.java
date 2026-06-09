package ru.ggkit.ch.prof.vmc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ggkit.ch.prof.vmc.entity.Sale;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {

}
