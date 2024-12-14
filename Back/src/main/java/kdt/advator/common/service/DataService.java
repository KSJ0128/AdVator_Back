package kdt.advator.common.service;

import kdt.advator.common.domain.*;
import kdt.advator.common.dto.DefaultInfoDTO;
import kdt.advator.common.dto.DistrictDTO;
import kdt.advator.common.dto.LocationDTO;
import kdt.advator.common.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.*;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class DataService {
    private final ApartRepository apartRepository;
    private final CompanyRepository companyRepository;
    private final IndustryRepository industryRepository;
    private final BusinessRepository businessRepository;
    private final RatingRepository ratingRepository;
    private final PeriodRepository periodRepository;
    private final CityRepository cityRepository;
    private final DistrictRepository districtRepository;
    private final AreaRepository areaRepository;
    private final String defaultURL = "https://dapi.kakao.com/v2/local/search/address.json";

    @Value("${kakao.api.key}") // application.yml에 Kakao API 키 저장
    private String kakaoApiKey;

    @Transactional
    public void saveApartGps() {
        List<Apart> apartList = apartRepository.findAll();

        for (Apart apart : apartList) {
            try {
                // 1. URL 인코딩 - URL로 사용할 수 없는 문자들 '%XX'의 형태로 변환
                // (ex) 한글의 address를 한글로 사용하기 때문에 인코딩을 해줘야 함
                String roadFullAddr = URLEncoder.encode(apart.getRoadAddress(), "UTF-8");

                // 2. 요청 url을 만들기
                String addr = defaultURL + "?query=" + roadFullAddr;

                // 3. URL 객체 생성
                URL url = new URL(addr);

                // 4. URL Connection 객체 생성
                URLConnection conn = url.openConnection();

                // 5. 헤더값 설정해주기
                conn.setRequestProperty("Authorization", "KakaoAK " + kakaoApiKey);

                // 6. StringBuffer에 값을 넣고 String 형태로 변환하고 jsonString을 return
                BufferedReader rd = null;
                rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

                StringBuffer docJson = new StringBuffer();
                String line;

                while ((line = rd.readLine()) != null) {
                    docJson.append(line);
                }
                rd.close();

                // 6. 응답 JSON 파싱
                String jsonString = docJson.toString();
                JSONObject jsonResponse = new JSONObject(jsonString);
                JSONArray documents = jsonResponse.getJSONArray("documents");

                if (documents.length() > 0) {
                    JSONObject firstDocument = documents.getJSONObject(0);
                    double x = firstDocument.getDouble("x");
                    double y = firstDocument.getDouble("y");

                    // 7. 좌표 업데이트
                    apart.setGpsX(x);
                    apart.setGpsY(y);
                    apartRepository.save(apart);
                    log.info("Updated GPS for {}: x={}, y={}", apart.getRoadAddress(), x, y);
                } else {
                    log.warn("No coordinates found for address: {}", apart.getRoadAddress());
                }

            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public DefaultInfoDTO getInfo() {
        List<String> company = companyRepository.findAll().stream()
                .map(Company::getName)
                .toList();

        List<String> industry = industryRepository.findAll().stream()
                .map(Industry::getName)
                .toList();

        List<String> business = businessRepository.findAll().stream()
                .map(Business::getName)
                .toList();

        List<String> rating = ratingRepository.findAll().stream()
                .map(Rating::getName)
                .toList();

        List<String> period = periodRepository.findAll().stream()
                .map(Period::getPeriod)
                .toList();

        return DefaultInfoDTO.builder()
                .company(company)
                .industry(industry)
                .business(business)
                .rating(rating)
                .period(period)
                .city(getLocations())
                .build();
    }

    private List<LocationDTO> getLocations() {
        // 1. 데이터베이스에서 city 객체 리스트 가져오기
        List<City> cities = cityRepository.findAll();

        // 2. 각 city에 대해 District와 Area 가져오기
        List<LocationDTO> locations = cities.stream()
                .map(city -> {
                    // 각 city에 해당하는 District 객체 가져오기
                    List<District> districts = districtRepository.findByCity(city);

                    // 각 District에 해당하는 Area 객체 처리
                    List<DistrictDTO> districtDTOs = districts.stream()
                            .map(district -> {
                                // 각 District에 해당하는 Area 객체 가져오기
                                List<Area> areas = areaRepository.findByDistrict(district);

                                // Area 객체에서 이름 추출
                                List<String> areaNames = areas.stream()
                                        .map(Area::getName)
                                        .toList();

                                // DistrictDTO 생성
                                return new DistrictDTO(district.getName(), areaNames);
                            })
                            .toList();

                    // LocationDTO 생성
                    return new LocationDTO(city.getName(), districtDTOs);
                })
                .toList();

        return locations;
    }
}
