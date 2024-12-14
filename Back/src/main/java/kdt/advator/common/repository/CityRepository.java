package kdt.advator.common.repository;

import kdt.advator.common.domain.Business;
import kdt.advator.common.domain.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Long> {
}
