package kdt.advator.estimate.controller;

import kdt.advator.estimate.dto.AdvertiseReq;
import kdt.advator.estimate.dto.AdvertiseRes;
import kdt.advator.estimate.service.EstimateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/estimate")
@RequiredArgsConstructor
public class EstimateControllerImpl implements EstimateController{
    private final EstimateService estimateService;

    @PostMapping("/full-ad")
    public ResponseEntity<AdvertiseRes> postFullAd(@RequestBody AdvertiseReq advertiseReq) {
        if (advertiseReq == null)
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);

        AdvertiseRes advertiseRes = estimateService.requestFullAdEstimate(advertiseReq);
        if (advertiseRes == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

        return ResponseEntity.status(HttpStatus.CREATED).body(advertiseRes);
    }
}
