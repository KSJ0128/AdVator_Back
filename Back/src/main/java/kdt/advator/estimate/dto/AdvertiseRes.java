package kdt.advator.estimate.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import kdt.advator.common.dto.ApartDTO;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Schema(description = "광고 견적 응답 DTO")
public class AdvertiseRes {
    @Schema(description = "아파트 이름 목록")
    private List<String> apartNames;

    @Schema(description = "업종", example = "요식업")
    private String industry;

    @Schema(description = "사업 유형", example = "프랜차이즈")
    private String business;

    @Schema(description = "광고 기간", example = "4")
    private String period;

    @Schema(description = "광고 시작일", example = "2025-01-01")
    private String start;

    @Schema(description = "요청 및 문의 사항", example = "광고 문의 드립니다.")
    private String description;

    @Schema(description = "작성자 이름", example = "김서진")
    private String author;

    @Schema(description = "연락처 번호", example = "010-1234-5678")
    private String phone;

    @Schema(description = "작성자 이메일", example = "ksj01128@naver.com")
    private String email;

    @Builder
    public AdvertiseRes(AdvertiseReq advertiseReq) {
        this.industry = advertiseReq.getIndustry();
        this.business = advertiseReq.getBusiness();
        this.period = advertiseReq.getPeriod();
        this.start = advertiseReq.getStart();
        this.description = advertiseReq.getDescription();
        this.author = advertiseReq.getAuthor();
        this.phone = advertiseReq.getPhone();
        this.email = advertiseReq.getEmail();
        this.apartNames = advertiseReq.getApart()
                .stream()
                .map(ApartDTO::getApartName)
                .toList();
    }
}
