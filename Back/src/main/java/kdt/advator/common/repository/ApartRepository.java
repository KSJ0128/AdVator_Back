package kdt.advator.common.repository;

import kdt.advator.common.domain.Apart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApartRepository extends JpaRepository<Apart, Long> {
    Apart findByApartName(String name);
}
