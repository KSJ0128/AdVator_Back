package kdt.advator.estimate.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import kdt.advator.estimate.dto.AdvertiseReq;
import kdt.advator.estimate.dto.AdvertiseRes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Estimate", description = "견적 API")

public interface EstimateController {
    @Operation(summary = "15초 광고 견적서 요청 API", description = "15초 광고 견적서를 요청하는 API입니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "견적 요청에 성공했습니다.", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "204", description = "견적 요청이 비어있습니다.", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "견적 요청에 실패했습니다.", content = @Content(mediaType = "application/json"))
    })
    public ResponseEntity<AdvertiseRes> postFullAd(@RequestBody AdvertiseReq advertiseReq);
}
