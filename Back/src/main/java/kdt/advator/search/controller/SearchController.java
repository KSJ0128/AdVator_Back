package kdt.advator.search.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import kdt.advator.common.dto.ApartDTO;
import kdt.advator.search.dto.SearchApartDTO;
import kdt.advator.search.dto.SearchGpsDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Tag(name = "Search", description = "건물 조회 API")
public interface SearchController {

    @Operation(summary = "지도 건물 조회 - 주소 API", description = "주소를 기준으로 지도 상의 건물을 조회하는 API입니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "건물 조회에 성공했습니다.", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "204", description = "건물이 존재하지 않습니다.", content = @Content(mediaType = "application/json"))
    })
    public ResponseEntity<List<ApartDTO>> getApartsByAddress(@RequestBody SearchApartDTO searchApartDTO);

    public ResponseEntity<List<ApartDTO>> getApartsByGps(@RequestBody SearchGpsDTO searchGpsDTO);
}
