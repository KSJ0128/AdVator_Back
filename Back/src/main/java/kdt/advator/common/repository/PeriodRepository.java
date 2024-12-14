package kdt.advator.common.repository;

import kdt.advator.common.domain.Period;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PeriodRepository extends JpaRepository<Period, Long> {
    Period findByPeriod(String period);
}
