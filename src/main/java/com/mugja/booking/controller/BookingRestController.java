package com.mugja.booking.controller;

import com.mugja.booking.dto.BookingRequestDto;
import com.mugja.booking.dto.BookingResponseDto;
import com.mugja.booking.service.BookingService;
import com.mugja.member.service.MemberService;
import com.mugja.member.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@RestController
@RequestMapping("/api")
public class BookingRestController {

    private final BookingService bookingService;
    private final SecurityService securityService;
    private final MemberService memberService;

    @Autowired
    public BookingRestController(BookingService bookingService, SecurityService securityService, MemberService memberService) {
        this.bookingService = bookingService;
        this.securityService = securityService;
        this.memberService = memberService;
    }

    // 예약정보 생성
    @PostMapping("/booking")
    public Long save(@RequestBody final BookingRequestDto params) {
        return bookingService.save(params);
    }

    // 예약 목록
    @GetMapping("/booking/{pageNo}")
    public ResponseEntity<Page<BookingResponseDto>> getBookList(@PathVariable Integer pageNo) {
        Pageable pageable = PageRequest.of(pageNo-1, 8);
        try {
            Page<BookingResponseDto> bookings = bookingService.findBookingsByMemberId(memberService.getMemId(), pageable);
            return new ResponseEntity<Page<BookingResponseDto>>(bookings, HttpStatus.OK);
        }catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
