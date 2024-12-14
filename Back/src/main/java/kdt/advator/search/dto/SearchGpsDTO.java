package kdt.advator.search.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.util.List;

@Getter
public class SearchGpsDTO {
    private Double gpsX;
    private Double gpsY;
    @Schema(description = "정렬 기준 (low: 저렴한 순, high: 비싼 순)", example = "low")
    private String sort;

    @Schema(description = "평점 기준", example = "A")
    private List<String> rating;

    @Schema(description = "광고 업체명", example = "포커스 미디어 코리아")
    private List<String> company;
}
