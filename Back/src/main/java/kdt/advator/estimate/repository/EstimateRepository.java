package kdt.advator.estimate.repository;

import kdt.advator.common.domain.Apart;
import kdt.advator.estimate.domain.Estimate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstimateRepository extends JpaRepository<Estimate, Long> {
    Boolean existsByApart(Apart apart);
    Estimate findByApart(Apart apart);
}
