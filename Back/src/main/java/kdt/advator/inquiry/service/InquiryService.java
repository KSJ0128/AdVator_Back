package kdt.advator.inquiry.service;

import kdt.advator.ad_company.domain.FocusMediaKorea;
import kdt.advator.ad_company.domain.KTTownBoard;
import kdt.advator.ad_company.domain.MediaMid;
import kdt.advator.ad_company.dto.InquiryDTO;
import kdt.advator.ad_company.repository.FocusMediaKoreaRepository;
import kdt.advator.ad_company.repository.KTTownBoardRepository;
import kdt.advator.ad_company.repository.MediaMidRepository;
import kdt.advator.common.domain.*;
import kdt.advator.estimate.domain.Estimate;
import kdt.advator.estimate.dto.AdvertiseReq;
import kdt.advator.common.dto.ApartDTO;
import kdt.advator.common.repository.*;
import kdt.advator.estimate.repository.EstimateRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class InquiryService {
    private final BusinessRepository businessRepository;
    private final IndustryRepository industryRepository;
    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;
    private final PeriodRepository periodRepository;
    private final ApartRepository apartRepository;
    private final FocusMediaKoreaRepository focusMediaKoreaRepository;
    private final KTTownBoardRepository ktTownBoardRepository;
    private final MediaMidRepository mediaMidRepository;
    private final EstimateRepository estimateRepository;

    @Transactional
    public User saveFullAdUser(AdvertiseReq advertiseReq) {
        Business business = businessRepository.findByName(advertiseReq.getBusiness());
        Industry industry = industryRepository.findByName(advertiseReq.getIndustry());

        if (business == null || industry == null) {
            log.info("업종 및 사업 유형 에러");
            return null;
        }

        User user = User.builder()
                .advertiseReq(advertiseReq)
                .business(business)
                .industry(industry)
                .build();

        userRepository.save(user);
        return user;
    }

//    @Transactional
//    public boolean saveSplitAdInquiry(AdvertiseReq advertiseReq, User user) {
//        List<SplitEstimate> estimateList = new ArrayList<>();
//
//        for (ApartDTO apartDTO : advertiseReq.getApart()) {
//            Apart apart = apartRepository.findByApartName(apartDTO.getApartName());
//            if (apart == null)
//                return false;
//
//            estimateList.add(new SplitEstimate(advertiseReq, apart, user));
//        }
//        splitEstimateRepository.saveAll(estimateList);
//        return true;
//    }
//
    @Transactional
    public InquiryDTO saveFullAdInquiry(AdvertiseReq advertiseReq, User user) {
        List<ApartDTO> apartList = advertiseReq.getApart();
        List<FocusMediaKorea> focusMediaKoreaList = new ArrayList<>();
        List<KTTownBoard> ktTownBoardList = new ArrayList<>();
        List<MediaMid> mediaMidList = new ArrayList<>();

        String focusAD = "포커스 미디어 코리아";
        String ktAD = "KT 타운보드";
        String mediaAD = "미디어 믿";

        for (ApartDTO apart : apartList) {
            Company company = companyRepository.findByName(apart.getCompany());

            if (company.getName().equals(focusAD)) {
                FocusMediaKorea focusMediaKorea = FocusMediaKorea.builder()
                        .apart(apartRepository.findByApartName(apart.getApartName()))
                        .user(user)
                        .period(periodRepository.findByPeriod(advertiseReq.getPeriod()))
                        .build();

                log.info("APT : {}", focusMediaKorea.getApart().getApartName());
                focusMediaKoreaList.add(focusMediaKorea);
            }
            else if (company.getName().equals(ktAD)) {
                KTTownBoard ktTownBoard = KTTownBoard.builder()
                        .apart(apartRepository.findByApartName(apart.getApartName()))
                        .user(user)
                        .period(periodRepository.findByPeriod(advertiseReq.getPeriod()))
                        .build();

                log.info("APT : {}", ktTownBoard.getApart().getApartName());
                ktTownBoardList.add(ktTownBoard);
            }
            else if (company.getName().equals(mediaAD)) {
                MediaMid mediaMid = MediaMid.builder()
                        .apart(apartRepository.findByApartName(apart.getApartName()))
                        .user(user)
                        .period(periodRepository.findByPeriod(advertiseReq.getPeriod()))
                        .build();

                log.info("APT : {}", mediaMid.getApart().getApartName());
                mediaMidList.add(mediaMid);
            }
            else
                log.info("광고 업체명 에러");
        }
        focusMediaKoreaRepository.saveAll(focusMediaKoreaList);
        ktTownBoardRepository.saveAll(ktTownBoardList);
        mediaMidRepository.saveAll(mediaMidList);

        return InquiryDTO.builder()
                .focusMediaKoreaList(focusMediaKoreaList)
                .ktTownBoardList(ktTownBoardList)
                .mediaMidList(mediaMidList)
                .build();
    }

    @Transactional
    public void addEstimate(AdvertiseReq advertiseReq) {
        for (ApartDTO apartDTO : advertiseReq.getApart()) {
            Estimate estimate = estimateRepository.findByApart(apartRepository.findByApartName(apartDTO.getApartName()));

            if (estimate == null) {
                estimate = new Estimate(apartRepository.findByApartName(apartDTO.getApartName()), 1L);
            }
            else {
                estimate.setRequest(estimate.getRequest() + 1);
            }
            estimateRepository.save(estimate);
        }
    }
}
