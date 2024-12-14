package kdt.advator.estimate.dto;

import kdt.advator.ad_company.domain.FocusMediaKorea;
import kdt.advator.ad_company.domain.KTTownBoard;
import kdt.advator.ad_company.domain.MediaMid;
import kdt.advator.ad_company.dto.InquiryDTO;
import kdt.advator.common.domain.Apart;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class EmailDTO {
    private String apartNames;
    private String start;
    private String period;

    @Builder
    public EmailDTO(InquiryDTO inquiryDTO, String company) {
        String focusAd = "포커스 미디어 코리아";
        String ktAd = "KT 타운보드";
        String mediaAd = "미디어 믿";

        if (company.equals(focusAd)) {
            List<FocusMediaKorea> focusMediaKoreaList = inquiryDTO.getFocusMediaKoreaList();

            this.apartNames = focusMediaKoreaList.stream()
                    .map(FocusMediaKorea::getApart)
                    .map(Apart::getApartName)
                    .collect(Collectors.joining(", "));
            this.start = focusMediaKoreaList.getFirst().getStart();
            this.period = focusMediaKoreaList.getFirst().getPeriod().getPeriod();
        }
        else if (company.equals(ktAd)) {
            List<KTTownBoard> ktTownBoardList = inquiryDTO.getKtTownBoardList();

            this.apartNames = ktTownBoardList.stream()
                    .map(KTTownBoard::getApart)
                    .map(Apart::getApartName)
                    .collect(Collectors.joining(", "));
            this.start = ktTownBoardList.getFirst().getStart();
            this.period = ktTownBoardList.getFirst().getPeriod().getPeriod();
        }
        else if (company.equals(mediaAd)) {
            List<MediaMid> mediaMidList = inquiryDTO.getMediaMidList();

            this.apartNames = mediaMidList.stream()
                    .map(MediaMid::getApart)
                    .map(Apart::getApartName)
                    .collect(Collectors.joining(", "));
            this.start = mediaMidList.getFirst().getStart();
            this.period = mediaMidList.getFirst().getPeriod().getPeriod();
        }
    }
}