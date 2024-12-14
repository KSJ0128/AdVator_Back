package kdt.advator.search.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.util.List;

@Getter
public class SearchGpsDTO {
    @Schema(description = "gpsX", example = "126.856365705707")
    private Double gpsX;
    @Schema(description = "gpsY", example = "37.5369416913176")
    private Double gpsY;
    @Schema(description = "정렬 기준 (low: 저렴한 순, high: 비싼 순)", example = "low")
    private String sort;

    @Schema(description = "평점 기준", example = "[\"S+\", \"A+\"]")
    private List<String> rating;

    @Schema(description = "광고 업체명", example = "[\"포커스 미디어 코리아\", \"미디어 믿\"]")
    private List<String> company;
}
