package com.mugja.booking.service;

import com.mugja.booking.dto.BookingRequestDto;
import com.mugja.booking.dto.BookingResponseDto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface BookingService {
    // 예약정보 생성 메서드
    Long save(BookingRequestDto params);

    // 페이징 처리 메서드
    Page<BookingResponseDto> findBookingsByMemberId(Integer memberId, Pageable pageable);

    // 체크인/체크아웃 날짜 형식 변환
    public String formatBookingDate(LocalDate bookingDate);

    // 객실가격 금액 형식 변환
    public String formatPayPrice(BigDecimal payPrice);


}