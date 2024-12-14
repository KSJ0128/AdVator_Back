package kdt.advator.common.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import kdt.advator.common.dto.DefaultInfoDTO;
import kdt.advator.common.service.DataService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/data")
@RequiredArgsConstructor
@Tag(name = "Data", description = "건물 조회 API")
public class DataController {
    private final DataService dataService;

//    @PostMapping("/gps")
//    public void saveApartGps() {
//        dataService.saveApartGps();
//    }

    @GetMapping("/info")
    @Operation(summary = "기본 정보 조회 API", description = "기본 정보를 조회하는 API입니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "기본 정보 조회에 성공했습니다.", content = @Content(mediaType = "application/json")),
    })
    public ResponseEntity<DefaultInfoDTO> getDefaultInfo() {
        return ResponseEntity.status(HttpStatus.OK).body(dataService.getInfo());
    }
}
