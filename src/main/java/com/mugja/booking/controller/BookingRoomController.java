package com.mugja.booking.controller;

import com.mugja.member.service.MemberService;
import com.mugja.member.service.SecurityService;
import com.mugja.room.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller()
public class BookingRoomController {

    @Autowired
    private SecurityService securityService;

    @Autowired
    private RoomService roomService;

    @Autowired
    private MemberService memberService;

    @GetMapping("/mugja/roomlistforbooking")
    public String showRoomList(Model model) {
        // 필요한 데이터를 모델에 추가
        return "view/roomlistforbooking";
    }
    
    @GetMapping("/mugja/booking/{hostId}/{roomId}/{payPrice}")
    public String submitBooking(@PathVariable String hostId,
                                @PathVariable String roomId,
                                @PathVariable String payPrice,
                                Model model) {
        
        // 전달 받은 값을 모델에 추가하여 booking.html로 전달
        model.addAttribute("memberId", memberService.getMemId());
        model.addAttribute("hostId", hostId);
        model.addAttribute("roomId", roomId);
        model.addAttribute("payPrice", payPrice);
        model.addAttribute("userid", securityService.userId()); // 결제기능 합치기
        return "view/booking";
    }
}
