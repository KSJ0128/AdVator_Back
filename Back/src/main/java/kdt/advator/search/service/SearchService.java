package kdt.advator.search.service;

import kdt.advator.common.domain.Apart;
import kdt.advator.common.dto.ApartDTO;
import kdt.advator.common.repository.ApartRepository;
import kdt.advator.estimate.domain.Estimate;
import kdt.advator.estimate.service.EstimateService;
import kdt.advator.search.dto.SearchApartDTO;
import kdt.advator.search.dto.SearchGpsDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class SearchService {
    private final ApartRepository apartRepository;
    private final EstimateService estimateService;
    

    public List<ApartDTO> getApartByAddress(SearchApartDTO searchApartDTO) {
        List<Apart> apartList = apartRepository.findByConditions(searchApartDTO.getCity(), searchApartDTO.getDistrict(), searchApartDTO.getArea(),
                searchApartDTO.getRating(), searchApartDTO.getCompany());

        if (apartList.isEmpty())
            return new ArrayList<>();

        List<Estimate> estimateList = estimateService.getEstimateList(apartList);
        if (estimateList.isEmpty())
            return new ArrayList<>();

        // DTO 리스트 생성 및 정렬
        return getApartDTOS(searchApartDTO, apartList, estimateList);
    }

    private List<ApartDTO> getApartDTOS(SearchApartDTO searchApartDTO, List<Apart> apartList, List<Estimate> estimateList) {
        Comparator<ApartDTO> priceComparator = Comparator.comparing(ApartDTO::getTotalPrice);
        if ("high".equalsIgnoreCase(searchApartDTO.getSort())) {
            priceComparator = priceComparator.reversed();
        }

        return apartList.stream()
                .map(apart -> ApartDTO.builder()
                        .apart(apart)
                        .estimate(findEstimateForApart(apart, estimateList))
                        .build())
                .sorted(priceComparator)
                .toList();
    }

    private Estimate findEstimateForApart(Apart apart, List<Estimate> estimateList) {
        return estimateList.stream()
                .filter(estimate -> estimate.getApart().equals(apart))
                .findFirst()
                .orElse(null); // Estimate가 없을 경우 null 반환
    }

    public List<ApartDTO> getApartByGps(SearchGpsDTO searchGpsDTO) {
        List<Apart> apartList = apartRepository.findByGpsXAndGpsY(searchGpsDTO.getGpsX(), searchGpsDTO.getGpsY(), searchGpsDTO.getRating(), searchGpsDTO.getCompany());

        if (apartList.isEmpty())
            return new ArrayList<>();

        List<Estimate> estimateList = estimateService.getEstimateList(apartList);
        if (estimateList.isEmpty())
            return new ArrayList<>();

        // DTO 리스트 생성 및 정렬
        return getApartDTOByGPS(searchGpsDTO, apartList, estimateList);
    }

    private List<ApartDTO> getApartDTOByGPS(SearchGpsDTO searchGpsDTO, List<Apart> apartList, List<Estimate> estimateList) {
        Comparator<ApartDTO> priceComparator = Comparator.comparing(ApartDTO::getTotalPrice);
        if ("high".equalsIgnoreCase(searchGpsDTO.getSort())) {
            priceComparator = priceComparator.reversed();
        }

        return apartList.stream()
                .map(apart -> ApartDTO.builder()
                        .apart(apart)
                        .estimate(findEstimateForApart(apart, estimateList))
                        .build())
                .sorted(priceComparator)
                .toList();
    }

}
