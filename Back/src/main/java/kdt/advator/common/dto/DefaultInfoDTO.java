package kdt.advator.common.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Schema(description = "기본 정보 응답")
public class DefaultInfoDTO {
    @Schema(description = "광고 업체 리스트")
    private final List<String> company;

    @Schema(description = "업종 리스트")
    private final List<String> industry;

    @Schema(description = "사업 유형 리스트")
    private final List<String> business;

    @Schema(description = "등급 리스트")
    private final List<String> rating;

    @Schema(description = "기간 리스트")
    private final List<String> period;

    @Schema(description = "도시 및 지역 정보 리스트")
    private final List<LocationDTO> city;

    @Builder
    public DefaultInfoDTO(List<String> company, List<String> industry, List<String> business, List<String> rating, List<String> period, List<LocationDTO> city) {
        this.company = company;
        this.industry = industry;
        this.business = business;
        this.rating = rating;
        this.period = period;
        this.city = city;
    }
}
