package kdt.advator.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class LocationDTO {
    private String city;
    private List<DistrictDTO> districtList;
}
