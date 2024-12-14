package kdt.advator.common.repository;

import kdt.advator.common.domain.Business;
import kdt.advator.common.domain.City;
import kdt.advator.common.domain.District;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DistrictRepository extends JpaRepository<District, Long> {
    List<District> findByCity(City city);
}
