package com.mugja.booking.controller;

import com.mugja.booking.dto.BookingResponseDto;
import com.mugja.booking.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller("/mugja")
public class ViewMyBookingController {

    private final BookingService bookingService;

    @Autowired
    public ViewMyBookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping("/mypage/bookinglist")
    public String getBookingList(@RequestParam Integer memberId,
                                 @RequestParam(defaultValue = "0") Integer page,
                                 @RequestParam(defaultValue = "5") Integer size,
                                 Model model) {

        Pageable pageable = PageRequest.of(page, size);
        Page<BookingResponseDto> bookings = bookingService.findBookingsByMemberId(memberId, pageable);

        model.addAttribute("bookings", bookings);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", bookings.getTotalPages());
        return "view/mypage/bookinglist";
    }
}
