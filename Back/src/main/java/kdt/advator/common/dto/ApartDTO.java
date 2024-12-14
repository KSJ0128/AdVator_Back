package kdt.advator.common.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import kdt.advator.common.domain.Apart;
import kdt.advator.estimate.domain.Estimate;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Schema(description = "아파트 DTO")
@NoArgsConstructor
public class ApartDTO {
    @Schema(description = "아파트 이름", example = "하나세인스톤3차")
    private String apartName;

    @Schema(description = "대당 가격", example = "13000")
    private Long unitPrice;

    @Schema(description = "TV 개수", example = "1")
    private Long tvCount;

    @Schema(description = "총 가격", example = "13000")
    private Long totalPrice;

    @Schema(description = "광고 업체 이름", example = "포커스 미디어 코리아")
    private String company;

    @Schema(description = "주소", example = "서울 구로구 공원로6길 16-22")
    private String address;

    @Schema(description = "평점", example = "S")
    private String rating;

    @Schema(description = "견적 요청 수", example = "10")
    private Long request;

    @Builder
    public ApartDTO(Apart apart, Estimate estimate) {
        this.apartName = apart.getApartName();
        this.unitPrice = apart.getUnitPrice();
        this.tvCount = apart.getTvCount();
        this.totalPrice = apart.getTotalPrice();
        this.company = apart.getCompany().getName();
        this.address = apart.getRoadAddress();
        this.rating = apart.getRating().getName();
        this.request = estimate.getRequest();
    }
}