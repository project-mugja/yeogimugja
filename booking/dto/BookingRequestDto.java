package com.mugja.booking.dto;

import com.mugja.host.dto.Host;
import com.mugja.room.dto.Room;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class BookingRequestDto {

    private Integer memberId; // 회원번호
    private Integer hostId; // 숙소번호
    private Integer roomId; // 객실번호
    private LocalDate checkInDate; // 체크인날짜
    private LocalDate checkOutDate; // 체크아웃날짜
    private String guestName; // 예약자명
    private String guestContact; // 예약자연락처
    private Integer payType; // 결제수단
    private BigDecimal payPrice; // 결제금액

    public Booking toEntity() {
        return Booking.builder()
                .memberId(memberId)
                .hostId(hostId)
                .roomId(roomId)
                .checkInDate(checkInDate)
                .checkOutDate(checkOutDate)
                .guestName(guestName)
                .guestContact(guestContact)
                .payType(payType)
                .payPrice(payPrice)
                .build();
    }
}