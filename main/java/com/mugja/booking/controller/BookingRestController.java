package com.mugja.booking.controller;

import com.mugja.booking.dto.BookingRequestDto;
import com.mugja.booking.dto.BookingResponseDto;
import com.mugja.booking.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BookingRestController {

    private final BookingService bookingService;

    @Autowired
    public BookingRestController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    // 예약정보 생성
    @PostMapping("/booking")
    public Long save(@RequestBody final BookingRequestDto params) {
        return bookingService.save(params);
    }

}
