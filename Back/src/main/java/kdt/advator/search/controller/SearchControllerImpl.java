package kdt.advator.search.controller;

import kdt.advator.common.dto.ApartDTO;
import kdt.advator.search.dto.SearchApartDTO;
import kdt.advator.search.dto.SearchGpsDTO;
import kdt.advator.search.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/search")
@RequiredArgsConstructor
public class SearchControllerImpl implements SearchController {
    private final SearchService searchService;

    @PostMapping("/address")
    public ResponseEntity<List<ApartDTO>> getApartsByAddress(@RequestBody SearchApartDTO searchApartDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(searchService.getApartByAddress(searchApartDTO));
    }

    @PostMapping("/gps")
    public ResponseEntity<List<ApartDTO>> getApartsByGps(@RequestBody SearchGpsDTO searchGpsDTO) {
        if (searchGpsDTO.getGpsX() == null || searchGpsDTO.getGpsY() == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

        return ResponseEntity.status(HttpStatus.OK).body(searchService.getApartByGps(searchGpsDTO));
    }

}
