package kdt.advator.common.repository;

import kdt.advator.common.domain.Area;
import kdt.advator.common.domain.District;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AreaRepository extends JpaRepository<Area, Long> {
    List<Area> findByDistrict(District district);
}
