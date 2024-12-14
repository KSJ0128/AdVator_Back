package kdt.advator.common.repository;

import kdt.advator.common.domain.Business;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusinessRepository extends JpaRepository<Business, Long> {
    Business findByName(String name);

}
