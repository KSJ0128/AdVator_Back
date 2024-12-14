package kdt.advator.common.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kdt.advator.common.domain.Apart;
import kdt.advator.common.domain.QApart;
import kdt.advator.estimate.domain.QEstimate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class ApartRepositoryImpl implements ApartRepositoryCustom{
    private final JPAQueryFactory queryFactory;

    @Override
    public List<Apart> findByConditions(String city, String district, String area, String rating, String company) {
        QApart a = QApart.apart;
        QEstimate e = QEstimate.estimate;
        BooleanBuilder conditions = new BooleanBuilder();

        if (city != null && !city.isEmpty())
            conditions.and(roadAddressContains(city));

        if (district != null && !district.isEmpty())
            conditions.and(roadAddressContains(district));

        if (area != null && !area.isEmpty())
            conditions.and(roadAddressContains(area));

        if (rating != null && !rating.isEmpty())
            conditions.and(ratingEquals(rating));

        if (company != null && !company.isEmpty())
            conditions.and(companyEquals(company));

        var query = queryFactory.selectFrom(a)
                .where(conditions);

        List<Apart> resultList = query.fetch();

        log.info("list size : {}", resultList.size());
        return new ArrayList<>(resultList);  // 중복 제거를 위해 Set으로 변환
    }

    private BooleanBuilder roadAddressContains(String value) {
        BooleanBuilder roadAddressConditions = new BooleanBuilder();
        QApart a = QApart.apart;

        roadAddressConditions.and(a.roadAddress.contains(value));
        return roadAddressConditions;
    }

    private BooleanBuilder ratingEquals(String rating) {
        BooleanBuilder ratingConditions = new BooleanBuilder();
        QApart a = QApart.apart;

        if (rating != null) {
            ratingConditions.and(a.rating.name.eq(rating));
        }

        return ratingConditions;
    }

    private BooleanBuilder companyEquals(String company) {
        BooleanBuilder companyConditions = new BooleanBuilder();
        QApart a = QApart.apart;

        if (company != null) {
            companyConditions.and(a.company.name.eq(company));
        }

        return companyConditions;
    }
}
