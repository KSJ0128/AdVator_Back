package kdt.advator.ad_company.dto;

import kdt.advator.ad_company.domain.FocusMediaKorea;
import kdt.advator.ad_company.domain.KTTownBoard;
import kdt.advator.ad_company.domain.MediaMid;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class InquiryDTO {
    List<FocusMediaKorea> focusMediaKoreaList;
    List<KTTownBoard> ktTownBoardList;
    List<MediaMid> mediaMidList;

    @Builder
    public InquiryDTO(List<FocusMediaKorea> focusMediaKoreaList,
                      List<KTTownBoard> ktTownBoardList,
                      List<MediaMid> mediaMidList) {
        this.focusMediaKoreaList = focusMediaKoreaList;
        this.ktTownBoardList = ktTownBoardList;
        this.mediaMidList = mediaMidList;
    }
}
