package alpb.domain;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

@Component
public class FlaskClient {

    private final RestTemplate restTemplate = new RestTemplate();

    // 특정 주소로 요청을 보내는 메서드
    public Map<String, Object> sendRequest(Long userId, String placeName) {
        
        // 요청 URL 설정
        String url = "http://52.231.100.58:5000/predict_by_name";  // Flask 서버의 엔드포인트 URL로 변경

        // 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // 요청 바디 설정
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("user_idx", userId);
        requestBody.put("place_name", placeName);

        // HttpEntity에 요청 헤더와 바디 추가
        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);

        // 요청 보내기
        ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, Map.class);

        // 응답 결과 반환
        return response.getBody();
    }
}