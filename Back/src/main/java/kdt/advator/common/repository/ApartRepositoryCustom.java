package kdt.advator.common.repository;

import kdt.advator.common.domain.Apart;

import java.util.List;

public interface ApartRepositoryCustom {
    List<Apart> findByConditions(String city, String district, String area, String rating, String company);
}
