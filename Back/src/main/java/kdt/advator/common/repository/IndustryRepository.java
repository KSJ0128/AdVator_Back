package kdt.advator.common.repository;

import kdt.advator.common.domain.Industry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IndustryRepository extends JpaRepository<Industry, Long> {
    Industry findByName(String name);
}
