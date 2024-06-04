package com.mugja.booking.controller;

import com.mugja.jwt.JwtUtils;
import com.mugja.member.service.MemberService;
import com.mugja.member.service.SecurityService;
import com.mugja.room.service.RoomService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
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

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserDetailsService userDetailsService;

    @GetMapping("/mugja/roomlistforbooking")
    public String showRoomList(Model model) {
        // 필요한 데이터를 모델에 추가
        return "view/roomlistforbooking";
    }
    
    @GetMapping("/booking/{hostId}/{roomId}/{payPrice}/{token}")
    public String submitBooking(@PathVariable String hostId,
                                @PathVariable String roomId,
                                @PathVariable String payPrice,
                                @PathVariable String token,
                                HttpServletRequest request,
                                Model model) {
        
        if (jwtUtils.validateToken(token)) {
            Claims claims = jwtUtils.getClaimsFromToken(token);
            String username = claims.getIssuer();

            System.out.println("username: " + username);

            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities());
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }


        System.out.println("memId : "+memberService.getMemId());
        // 전달 받은 값을 모델에 추가하여 booking.html로 전달
        model.addAttribute("memberId", memberService.getMemId());
        model.addAttribute("hostId", hostId);
        model.addAttribute("roomId", roomId);
        model.addAttribute("payPrice", payPrice);
        model.addAttribute("userid", securityService.userId()); // 결제기능 합치기
        return "view/booking";
    }
}
