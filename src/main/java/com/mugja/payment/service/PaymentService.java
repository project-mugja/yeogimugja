package com.mugja.payment.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class PaymentService {

    @Value("${iamport.key}")
    private String IMP_KEY;

    @Value("${iamport.secret}")
    private String IMP_SECRET;

    private static final String TOKEN_URL = "https://api.iamport.kr/users/getToken";
    private static final String PAYMENT_URL = "https://api.iamport.kr/payments/{imp_uid}";

    public String getToken() {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, String> body = new HashMap<>();
        body.put("imp_key", IMP_KEY);
        body.put("imp_secret", IMP_SECRET);

        HttpEntity<Map<String, String>> entity = new HttpEntity<>(body, headers);

        ResponseEntity<Map> response = restTemplate.postForEntity(TOKEN_URL, entity, Map.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            Map<String, Object> responseBody = response.getBody();
            if (responseBody != null && responseBody.get("response") != null) {
                Map<String, Object> responseData = (Map<String, Object>) responseBody.get("response");
                return (String) responseData.get("access_token");
            }
        }
//        ResponseEntity<Map> response = restTemplate.postForEntity(TOKEN_URL, entity, Map.class);
//
//        if (response.getStatusCode() == HttpStatus.OK) {
//            Map<String, Object> responseBody = response.getBody();
//            if (responseBody != null && responseBody.get("response") != null) {
//                Map<String, Object> responseData = (Map<String, Object>) responseBody.get("response");
//                return (String) responseData.get("access_token");
//            }
//        }

        throw new RuntimeException("Failed to get Iamport token");
    }

    public Map<String, Object> getPaymentInfo(String impUid) {
        String token = getToken();

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<Map> response = restTemplate.exchange(PAYMENT_URL, HttpMethod.GET, entity, Map.class, impUid);
        if (response.getStatusCode() == HttpStatus.OK) {
            Map<String, Object> responseBody = response.getBody();
            if (responseBody != null && responseBody.get("response") != null) {
                return (Map<String, Object>) responseBody.get("response");
            }
        }

        throw new RuntimeException("Failed to get payment info");
    }

//@Service
//public class PaymentService {
//
//    @Value("${iamport.key")
//    private String IMP_KEY;
//
//    @Value("${iamport.secret")
//    private String IMP_SECRET;
//
//    private static final String TOKEN_URL = "https://api.iamport.kr/users/getToken";
//    private static final String PAYMENT_URL = "https://api.iamport.kr/payments/{imp_uid}";
//
//    public String getToken() {
//        RestTemplate restTemplate = new RestTemplate();
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        System.out.println("Headers: " + headers);
//
//        Map<String, String> body = new HashMap<>();
//        body.put("imp_key", IMP_KEY);
//        body.put("imp_secret", IMP_SECRET);
//        System.out.println("Request Body: " + body);
//
//        HttpEntity<Map<String, String>> entity = new HttpEntity<>(body, headers);
//        System.out.println("------1------");
//        ResponseEntity<Map> response = restTemplate.postForEntity(TOKEN_URL,entity, Map.class);
//        System.out.println("------2------");
//        if (response.getStatusCode() == HttpStatus.OK) {
//        	System.out.println("------3------");
//            Map<String, Object> responseBody = response.getBody();
//            if (responseBody != null && responseBody.get("response") != null) {
//                Map<String, Object> responseData = (Map<String, Object>) responseBody.get("response");
//                return (String) responseData.get("access_token");
//            }
//        }
//
//        throw new RuntimeException("Failed to get Iamport token");
//    }
//
//    public Map<String, Object> getPaymentInfo(String impUid) {
//        String token = getToken();
//        System.out.println("token: " + token);
//
//        RestTemplate restTemplate = new RestTemplate();
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.setBearerAuth(token);
//
//        HttpEntity<String> entity = new HttpEntity<>(headers);
//
//        ResponseEntity<Map> response = restTemplate.exchange(PAYMENT_URL, HttpMethod.GET, entity, Map.class, impUid);
//        if (response.getStatusCode() == HttpStatus.OK) {
//            Map<String, Object> responseBody = response.getBody();
//            if (responseBody != null && responseBody.get("response") != null) {
//                return (Map<String, Object>) responseBody.get("response");
//            }
//        }
//
//        throw new RuntimeException("Failed to get payment info");
//    }
	
	
    
    public boolean verifyPayment(String impUid, String merchantUid) {
        RestTemplate restTemplate = new RestTemplate();

        try {
            // 토큰 발급
            String url = "https://api.iamport.kr/users/getToken";
            Map<String, String> tokenRequest = new HashMap<>();
            tokenRequest.put("imp_key", "7637670348464136");
            tokenRequest.put("imp_secret", "i8cNg6JKWRmRTKDjiCOlPuROVLAUPSO9cNO7VIJMBQfAP1UwFFxRxHFwsO6t1xd1DShFsR7bqLGaEvqZ");

            String tokenResponseStr = restTemplate.postForObject(url, tokenRequest, String.class);
            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> tokenResponse = mapper.readValue(tokenResponseStr, Map.class);

            // 토큰 발급 응답에서 access_token 추출
            Map<String, Object> responseMap = (Map<String, Object>) tokenResponse.get("response");
            String accessToken = (String) responseMap.get("access_token");

            // 결제 검증
            String verifyUrl = "https://api.iamport.kr/payments/" + impUid;
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", accessToken);
            HttpEntity<String> entity = new HttpEntity<>(headers);

            ResponseEntity<String> responseEntity = restTemplate.exchange(verifyUrl, HttpMethod.GET, entity, String.class);
            String responseStr = responseEntity.getBody();
            Map<String, Object> response = mapper.readValue(responseStr, Map.class);

            if ("paid".equals(response.get("status")) && merchantUid.equals(response.get("merchant_uid"))) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}