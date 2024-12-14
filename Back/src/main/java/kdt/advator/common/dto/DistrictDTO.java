package kdt.advator.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class DistrictDTO {
    private String district;
    private List<String> area;
}
