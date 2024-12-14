package kdt.advator.search.controller;

import kdt.advator.common.dto.ApartDTO;
import kdt.advator.search.dto.SearchApartDTO;
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
        List<ApartDTO> apartDTOList = searchService.getApartByAddress(searchApartDTO);
        if (apartDTOList == null)
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        return ResponseEntity.status(HttpStatus.OK).body(apartDTOList);
    }
}
