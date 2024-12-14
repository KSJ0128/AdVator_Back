package kdt.advator.estimate.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import kdt.advator.common.dto.ApartDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Schema(description = "15초 광고 견적 요청 DTO")
@NoArgsConstructor
public class AdvertiseReq {
    @Schema(description = "아파트 목록")
    private List<ApartDTO> apart;

    @Schema(description = "광고 요청 업체", example = "스타벅스 마곡나루공원점")
    private String store;

    @Schema(description = "동영상 링크 또는 정보", example = "https://example.com/video")
    private String video;

    @Schema(description = "업종", example = "프랜차이즈")
    private String industry;

    @Schema(description = "사업 유형", example = "요식업")
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

    @Schema(description = "개인 정보 제공 동의 여부", example = "true")
    private Boolean personalAgree;

    @Schema(description = "마케팅 활용 동의 여부", example = "true")
    private Boolean marketingAgree;
}
