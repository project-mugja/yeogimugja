package com.mugja.booking.service;

import com.mugja.booking.domain.BookingRepository;
import com.mugja.booking.dto.BookingRequestDto;
import com.mugja.booking.dto.BookingResponseDto;
import com.mugja.booking.dto.Booking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;

    @Autowired
    public BookingServiceImpl(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    public Long save(BookingRequestDto params) {
        Booking booking = params.toEntity();
        bookingRepository.save(booking);
        return (long) booking.getBookId();
    }

    @Override
    public Page<BookingResponseDto> findBookingsByMemberId(Integer memberId, Pageable pageable) {
        return bookingRepository.findByMemberIdOrderByPayDateDesc(memberId, pageable)
                .map(BookingResponseDto::new);
    }

    @Override
    public String formatBookingDate(LocalDate bookingDate) {
        return bookingDate.format(DateTimeFormatter.ofPattern("yyyy년 M월 d일"));
    }

    @Override
    public String formatPayPrice(BigDecimal payPrice) {
        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.KOREA);
        return numberFormat.format(payPrice) + "원";
    }

}
