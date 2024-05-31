package com.mugja.booking.service;

import com.mugja.booking.domain.BookingRepository;
import com.mugja.booking.dto.BookingRequestDto;
import com.mugja.booking.dto.BookingResponseDto;
import com.mugja.booking.dto.Booking;

import com.mugja.host.domain.HostRepository;
import com.mugja.host.dto.Host;
import com.mugja.room.domain.RoomRepository;
import com.mugja.room.dto.Room;
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
    private final HostRepository hostRepository;
    private final RoomRepository roomRepository;

    @Autowired
    public BookingServiceImpl(BookingRepository bookingRepository, HostRepository hostRepository, RoomRepository roomRepository) {
        this.bookingRepository = bookingRepository;
        this.hostRepository = hostRepository;
        this.roomRepository = roomRepository;
    }

    // 예약정보 생성
    @Override
    public Long save(BookingRequestDto params) {
        Booking booking = params.toEntity();
        bookingRepository.save(booking);
        return (long) booking.getBookId();
    }
    
    // 예약목록 페이징 처리
    @Override
    public Page<BookingResponseDto> findBookingsByMemberId(Integer memberId, Pageable pageable) {
        Page<Booking> bookings = bookingRepository.findByMemberIdOrderByPayDateDesc(memberId, pageable);
        return bookings.map(booking -> {
            BookingResponseDto dto = new BookingResponseDto(booking);

            Host host = hostRepository.findById((booking.getHostId())).orElse(null);
            Room room = roomRepository.findById((booking.getRoomId())).orElse(null);

            if (host != null) {
                dto.setHostName(host.getHostName());
            }

            if (room != null) {
                dto.setRoomName(room.getName());
            }

            dto.setFormattedCheckInDate(formatBookingDate(booking.getCheckInDate()));
            dto.setFormattedCheckOutDate(formatBookingDate(booking.getCheckOutDate()));
            dto.setFormattedPayPrice(formatPayPrice(booking.getPayPrice()));
            dto.setFormattedBookStatus(formatBookStatus(booking.getBookStatus()));

            return dto;
        });
    }

    // 체크인/체크아웃 날짜 형식 변환
    @Override
    public String formatBookingDate(LocalDate bookingDate) {
        return bookingDate.format(DateTimeFormatter.ofPattern("yyyy년 M월 d일"));
    }

    // 객실가격 금액 형식 변환
    @Override
    public String formatPayPrice(BigDecimal payPrice) {
        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.KOREA);
        return numberFormat.format(payPrice) + "원";
    }

    // 예약상태 한글로 변경
    private String formatBookStatus(Integer bookStatus) {
        switch (bookStatus) {
            case 20:
                return "이용완료";
            default:
                return "예약완료";
        }
    }

}
