package kdt.advator.common.controller;

import kdt.advator.common.service.CommonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/data")
@RequiredArgsConstructor
public class CommonController {
    private final CommonService commonService;

    @PostMapping("/gps")
    public void saveApartGps() {
        commonService.saveApartGps();
    }
}
