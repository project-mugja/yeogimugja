package com.mugja.booking.dto;

import com.mugja.host.dto.Host;
import com.mugja.room.dto.Room;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class BookingResponseDto {
    private Integer bookId;
    private Integer memberId;
    private Integer hostId;
    private Integer roomId;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private Integer bookStatus;
    private String guestName;
    private String guestContact;
    private Integer payType;
    private BigDecimal payPrice;
    private LocalDateTime payDate;

    @Setter
    private String formattedCheckInDate;

    @Setter
    private String formattedCheckOutDate;

    @Setter
    private String formattedPayPrice;

    public BookingResponseDto(Booking entity) {
        this.bookId = entity.getBookId();
        this.memberId = entity.getMemberId();
        this.hostId = entity.getHostId();
        this.roomId = entity.getRoomId();
        this.checkInDate = entity.getCheckInDate();
        this.checkOutDate = entity.getCheckOutDate();
        this.bookStatus = entity.getBookStatus();
        this.guestName = entity.getGuestName();
        this.guestContact = entity.getGuestContact();
        this.payType = entity.getPayType();
        this.payPrice = entity.getPayPrice();
        this.payDate = entity.getPayDate();
    }

}
