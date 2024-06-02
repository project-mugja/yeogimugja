	package com.mugja.payment.controller;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mugja.payment.dto.PayDto;
import com.mugja.payment.service.PaymentService;

@RestController
@RequestMapping("/payments")
public class PayRestContoller {
	
	@Autowired
    private final PaymentService paymentService;

    public PayRestContoller(PaymentService paymentService) {
        this.paymentService = paymentService;
    }


    @PostMapping("/completetest")
    public Map<String, Object> completetest(@RequestBody Map<String, String> request,PayDto dto) {
        String impUid = request.get("imp_uid");
        String merchantUid = request.get("merchant_uid");
        System.out.println(request.get("book_price"));
        System.out.println(request.get("book_name"));
        System.out.println(request.get("book_contact"));
        
        
        Map<String, Object> response = new HashMap<>();
        try {
            Map<String, Object> paymentInfo = paymentService.getPaymentInfo(impUid);

            if (paymentInfo != null && "paid".equals(paymentInfo.get("status"))) {
                response.put("success", true);
            } else {
                response.put("success", false);
                response.put("error", "결제 정보가 유효하지 않습니다.");
            }
        } catch (Exception e) {
            response.put("success", false);
            response.put("error", e.getMessage());
        }

        return response;
    }
    
    @PostMapping("/complete")
    public ResponseEntity<Map<String, Object>> completePayment(@RequestBody Map<String, String> params) {
        String impUid = params.get("imp_uid");
        String merchantUid = params.get("merchant_uid");
        
        boolean isSuccess = paymentService.verifyPayment(impUid, merchantUid);

        Map<String, Object> response = new HashMap<>();
        response.put("success", isSuccess);
        if (!isSuccess) {
            response.put("error", "결제 검증 실패");
        }

        return ResponseEntity.ok(response);
    }
}
