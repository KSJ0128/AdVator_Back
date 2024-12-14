package kdt.advator.common.service;

import kdt.advator.common.domain.Apart;
import kdt.advator.common.repository.ApartRepository;
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
public class CommonService {
    private final ApartRepository apartRepository;
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
}
