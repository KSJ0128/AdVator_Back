package kdt.advator.estimate.service;

import jakarta.mail.MessagingException;
import kdt.advator.ad_company.domain.FocusMediaKorea;
import kdt.advator.ad_company.domain.KTTownBoard;
import kdt.advator.ad_company.domain.MediaMid;
import kdt.advator.ad_company.dto.InquiryDTO;
import kdt.advator.common.domain.Apart;
import kdt.advator.estimate.domain.Estimate;
import kdt.advator.estimate.dto.AdvertiseReq;
import kdt.advator.estimate.dto.AdvertiseRes;
import kdt.advator.estimate.repository.EstimateRepository;
import kdt.advator.inquiry.service.InquiryService;
import kdt.advator.common.domain.User;
import kdt.advator.mail.service.MailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class EstimateService {
    private final InquiryService inquiryService;
    private final MailService mailService;
    private final EstimateRepository estimateRepository;

    public AdvertiseRes requestFullAdEstimate(AdvertiseReq advertiseReq) {
        // 광고 업체 서버에 저장 - 요청 사용자 정보 저장
        User user = inquiryService.saveFullAdUser(advertiseReq);
        if (user == null) {
            log.info("유저 정보 생성 실패");
            return null;
        }

        // inquiry 정보 추가 견적 요청 수 증가
        InquiryDTO inquiryDTO = inquiryService.saveFullAdInquiry(advertiseReq, user);
        if (inquiryDTO == null) {
            log.info("견적 생성 실패");
            return null;
        }

        // 아파트 별 견적 요청 수 증가
        inquiryService.addEstimate(advertiseReq);

        // 메일 전송
        sendMailToAdServer(user, inquiryDTO);
        return new AdvertiseRes(advertiseReq);
    }

//    public AdvertiseRes requestSplitAdEstimate(AdvertiseReq advertiseReq) {
//        // 광고 업체 서버에 저장 - 요청 사용자 정보 저장
//        User user = inquiryService.saveFullAdUser(advertiseReq);
//        if (user == null) {
//            log.info("유저 정보 생성 실패");
//            return null;
//        }
//
//        // 광고 업체 서버에 저장 - 5초 광고 DB에 저장
//        if (!inquiryService.saveSplitAdInquiry(advertiseReq, user)) {
//            log.info("견적 생성 실패");
//            return null;
//        }
//
//        // 아파트 별 견적 요청 수 증가
//        inquiryService.addEstimate(advertiseReq);
//
//        // 같은 아파트 단지 견적 문의 3명 생기면 3명의 사장님에게 이메일 전송(광고 집행 하려고 하니 영상 전송 부탁) - 아파트 단지, 업체 사장님 3명 정보
//        // N명의 사장님 n 카운트
//        checkSplitAd();
//    }
//
//    private void checkSplitAd() {
//        List<SplitEstimate>
//    }

    private void sendMailToAdServer(User user, InquiryDTO inquiryDTO) {
        sendToFocusServer(inquiryDTO, user);
        sendToKTServer(inquiryDTO, user);
        sendToMediaServer(inquiryDTO, user);
    }

    private void sendToFocusServer(InquiryDTO inquiryDTO, User user) {
        List<FocusMediaKorea> focusMediaKoreaList = inquiryDTO.getFocusMediaKoreaList();
        String toEmail = "20201028@sungshin.ac.kr";
        String focusAd = "포커스 미디어 코리아";
        StringBuilder aparts = new StringBuilder();

        for (int i = 0; i < focusMediaKoreaList.size(); i++) {
            FocusMediaKorea focusMediaKorea = focusMediaKoreaList.get(i);
            aparts.append(focusMediaKorea.getApart().getApartName());

            // 마지막 요소가 아니면 , 추가
            if (i < focusMediaKoreaList.size() - 1) {
                aparts.append(", ");
            }
        }

        try {
            mailService.joinEmail(user, toEmail, focusAd, inquiryDTO);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    private void sendToKTServer(InquiryDTO inquiryDTO, User user) {
        List<KTTownBoard> ktTownBoardList = inquiryDTO.getKtTownBoardList();
        String toEmail = "20201028@sungshin.ac.kr";
        String ktAd = "KT 타운보드";
        StringBuilder aparts = new StringBuilder();

        for (int i = 0; i < ktTownBoardList.size(); i++) {
            KTTownBoard ktTownBoard = ktTownBoardList.get(i);
            aparts.append(ktTownBoard.getApart().getApartName());

            // 마지막 요소가 아니면 , 추가
            if (i < ktTownBoardList.size() - 1) {
                aparts.append(", ");
            }
        }

        try {
            mailService.joinEmail(user, toEmail, ktAd, inquiryDTO);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    private void sendToMediaServer(InquiryDTO inquiryDTO, User user) {
        List<MediaMid> mediaMidList = inquiryDTO.getMediaMidList();
        String toEmail = "20201028@sungshin.ac.kr";
        String mediaAd = "미디어 믿";
        StringBuilder aparts = new StringBuilder();

        for (int i = 0; i < mediaMidList.size(); i++) {
            MediaMid mediaMid = mediaMidList.get(i);
            aparts.append(mediaMid.getApart().getApartName());

            // 마지막 요소가 아니면 , 추가
            if (i < mediaMidList.size() - 1) {
                aparts.append(", ");
            }
        }

        try {
            mailService.joinEmail(user, toEmail, mediaAd, inquiryDTO);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    @Transactional
    public List<Estimate> getEstimateList(List<Apart> apartList) {
        List<Estimate> estimateList = new ArrayList<>();
        for (Apart apart : apartList) {
            if (!estimateRepository.existsByApart(apart))
                estimateRepository.save(new Estimate(apart, 0L));
            estimateList.add(estimateRepository.findByApart(apart));
        }
        log.info("Estimate size : {}", estimateList.size());

        return estimateList;
    }
}
