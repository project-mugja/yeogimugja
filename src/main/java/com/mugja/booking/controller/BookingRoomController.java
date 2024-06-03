package com.mugja.booking.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BookingRoomController {
    
    @GetMapping("/roomlist")
    public String showRoomList(Model model) {
        // 필요한 데이터를 모델에 추가
        return "view/roomlist";
    }
    
    @PostMapping("/booking")
    public String submitBooking(@RequestParam String memberId,
                                @RequestParam String hostId,
                                @RequestParam String roomId,
                                @RequestParam String payPrice,
                                Model model) {
        
        // 전달 받은 값을 모델에 추가하여 booking.html로 전달
        model.addAttribute("memberId", memberId);
        model.addAttribute("hostId", hostId);
        model.addAttribute("roomId", roomId);
        model.addAttribute("payPrice", payPrice);
        return "view/booking";
    }
}
