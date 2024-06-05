package com.mugja.payment.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.mugja.payment.dto.PayDto;
import com.mugja.payment.service.PaymentService;

//@RestController
//@RequestMapping("/mugja")
//public class PayRestController {
//
//    private final PaymentService paymentService;
//
//    @Autowired
//    public PayRestController(PaymentService paymentService) {
//        this.paymentService = paymentService;
//    }
//
//    @PostMapping("/completetest")
//    public ResponseEntity<Map<String, Object>> completetest(@RequestBody Map<String, String> request) {
//
//        String impUid = request.get("imp_uid");
//        String merchantUid = request.get("merchant_uid");
//
//        Map<String, Object> response = new HashMap<>();
//        try {
//            Map<String, Object> paymentInfo = paymentService.getPaymentInfo(impUid);
//            if (paymentInfo != null && "paid".equals(paymentInfo.get("status"))) {
//                // 결제가 성공했으므로 예약 정보를 저장
//                RestTemplate restTemplate = new RestTemplate();
//                String bookingUrl = "http://localhost:8090/api/addbooking/new";
//
//                Map<String, String> bookingRequest = new HashMap<>();
//                bookingRequest.put("memberId", request.get("memberId"));
//                bookingRequest.put("hostId", request.get("hostId"));
//                bookingRequest.put("roomId", request.get("roomId"));
//                bookingRequest.put("payPrice", request.get("payPrice"));
//                bookingRequest.put("checkInDate", request.get("checkInDate"));
//                bookingRequest.put("checkOutDate", request.get("checkOutDate"));
//                bookingRequest.put("guestName", request.get("guestName"));
//                bookingRequest.put("guestContact", request.get("guestContact"));
//                bookingRequest.put("payType", request.get("payType"));
//
//                ResponseEntity<Long> bookingResponse = restTemplate.postForEntity(bookingUrl, bookingRequest, Long.class);
//
//                if (bookingResponse.getStatusCode().is2xxSuccessful()) {
//                    response.put("success", true);
//                } else {
//                    response.put("success", false);
//                    response.put("error", "예약 정보 저장에 실패했습니다.");
//                }
//            } else {
//                response.put("success", false);
//                response.put("error", "결제 정보가 유효하지 않습니다.");
//            }
//        } catch (Exception e) {
//            response.put("success", false);
//            response.put("error", e.getMessage());
//        }
//
//        return ResponseEntity.ok(response);
//    }
//}
//
@RestController
@RequestMapping("/mugja")
public class PayRestController {

    private final PaymentService paymentService;

    @Autowired
    public PayRestController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/completetest")
    public ResponseEntity<Map<String, Object>> completetest(@RequestBody Map<String, String> request) {

        String impUid = request.get("imp_uid");
        String merchantUid = request.get("merchant_uid");

        Map<String, Object> response = new HashMap<>();
//        try {
//            Map<String, Object> paymentInfo = paymentService.getPaymentInfo(impUid);
//            if (paymentInfo != null && "paid".equals(paymentInfo.get("status"))) {
//                // 결제가 성공했으므로 예약 정보를 저장
//                RestTemplate restTemplate = new RestTemplate();
//                String bookingUrl = "http://localhost:8090/api/addbooking/new";
//
//                Map<String, String> bookingRequest = new HashMap<>();
//                bookingRequest.put("memberId", request.get("memberId"));
//                bookingRequest.put("hostId", request.get("hostId"));
//                bookingRequest.put("roomId", request.get("roomId"));
//                bookingRequest.put("payPrice", request.get("payPrice"));
//                bookingRequest.put("checkInDate", request.get("checkInDate"));
//                bookingRequest.put("checkOutDate", request.get("checkOutDate"));
//                bookingRequest.put("guestName", request.get("guestName"));
//                bookingRequest.put("guestContact", request.get("guestContact"));
//                bookingRequest.put("payType", request.get("payType"));
//
//                ResponseEntity<Long> bookingResponse = restTemplate.postForEntity(bookingUrl, bookingRequest, Long.class);
//
//                if (bookingResponse.getStatusCode().is2xxSuccessful()) {
//                    response.put("success", true);
//                } else {
//                    response.put("success", false);
//                    response.put("error", "예약 정보 저장에 실패했습니다.");
//                }
//            } else {
//                response.put("success", false);
//                response.put("error", "결제 정보가 유효하지 않습니다.");
//            }
//        } catch (Exception e) {
//            response.put("success", false);
//            response.put("error", e.getMessage());
//        }
            Map<String, Object> paymentInfo = paymentService.getPaymentInfo(impUid);

            // 결제가 성공했으므로 예약 정보를 저장
            RestTemplate restTemplate = new RestTemplate();
            String bookingUrl = "http://localhost:8090/api/addbooking/new";

            Map<String, String> bookingRequest = new HashMap<>();
            bookingRequest.put("memberId", request.get("memberId"));
            bookingRequest.put("hostId", request.get("hostId"));
            bookingRequest.put("roomId", request.get("roomId"));
            bookingRequest.put("payPrice", request.get("payPrice"));
            bookingRequest.put("checkInDate", request.get("checkInDate"));
            bookingRequest.put("checkOutDate", request.get("checkOutDate"));
            bookingRequest.put("guestName", request.get("guestName"));
            bookingRequest.put("guestContact", request.get("guestContact"));
            bookingRequest.put("payType", request.get("payType"));

            ResponseEntity<Long> bookingResponse = restTemplate.postForEntity(bookingUrl, bookingRequest, Long.class);


            response.put("success", true);

            return ResponseEntity.ok(response);


    }
}