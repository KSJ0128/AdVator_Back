package kdt.advator.common.repository;

import kdt.advator.common.domain.Business;
import kdt.advator.common.domain.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<Rating, Long> {
}
