package kdt.advator.search.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.util.List;

@Getter
@Schema(description = "지도 건물 조회 - 주소 DTO")
public class SearchApartDTO {
    @Schema(description = "시 이름", example = "서울특별시")
    private String city;

    @Schema(description = "구 이름", example = "강서구")
    private String district;

    @Schema(description = "동 이름", example = "곰달래로")
    private String area;

    @Schema(description = "정렬 기준 (low: 저렴한 순, high: 비싼 순)", example = "low")
    private String sort;

    @Schema(description = "평점 기준", example = "[A, S]")
    private List<String> rating;

    @Schema(description = "광고 업체명", example = "[포커스 미디어 코리아, 미디어 믿]")
    private List<String> company;
}
