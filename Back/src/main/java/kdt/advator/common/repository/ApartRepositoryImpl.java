package kdt.advator.common.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kdt.advator.common.domain.Apart;
import kdt.advator.common.domain.QApart;
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
    public List<Apart> findByConditions(String city, String district, String area, List<String> rating, List<String> company) {
        QApart a = QApart.apart;
        BooleanBuilder conditions = new BooleanBuilder();

        if (city != null && !city.isEmpty())
            conditions.and(lotAddressContains(city));

        if (district != null && !district.isEmpty())
            conditions.and(lotAddressContains(district));

        if (area != null && !area.isEmpty())
            conditions.and(lotAddressContains(area));

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

    @Override
    public List<Apart> findByGpsXAndGpsY(Double gpsX, Double gpsY, List<String> rating, List<String> company) {
        QApart a = QApart.apart;
        // 지구 반경 (km)
        Double earthRadius = 6371.0;
        // 고정 반경 값 설정 (1km)
        Double radius = 1.0;

        // Haversine 공식 구현
        NumberExpression<Double> distanceExpression = Expressions.numberTemplate(Double.class,
                "{0} * acos(cos(radians({1})) * cos(radians({2})) * cos(radians({3}) - radians({4})) + sin(radians({1})) * sin(radians({2})))",
                earthRadius, gpsY, a.gpsY, a.gpsX, gpsX);

        // 조건 빌더 생성
        BooleanBuilder conditions = new BooleanBuilder();
        conditions.and(distanceExpression.loe(radius)); // 반경 조건 추가

        if (rating != null && !rating.isEmpty()) {
            conditions.and(ratingEquals(rating));
        }

        if (company != null && !company.isEmpty()) {
            conditions.and(companyEquals(company));
        }

        // QueryDSL 쿼리
        return queryFactory.selectFrom(a)
                .where(conditions)
                .fetch();
    }

    private BooleanBuilder lotAddressContains(String value) {
        BooleanBuilder lotAddressConditions = new BooleanBuilder();
        QApart a = QApart.apart;

        lotAddressConditions.and(a.lotAddress.contains(value));
        return lotAddressConditions;
    }

    private BooleanBuilder ratingEquals(List<String> ratingList) {
        BooleanBuilder ratingConditions = new BooleanBuilder();
        QApart a = QApart.apart;

        if (ratingList != null) {
            for (String rating : ratingList) {
                ratingConditions.or(a.rating.name.eq(rating));
            }
        }

        return ratingConditions;
    }

    private BooleanBuilder companyEquals(List<String> companyList) {
        BooleanBuilder companyConditions = new BooleanBuilder();
        QApart a = QApart.apart;

        if (companyList != null) {
            for (String company : companyList) {
                companyConditions.or(a.company.name.eq(company));
            }
        }

        return companyConditions;
    }
}
