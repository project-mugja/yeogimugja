package com.mugja.booking.dto;

import com.mugja.host.dto.Host;
import com.mugja.room.dto.Room;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "Book")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BOOK_ID")
    private Integer bookId; //예약번호 PK

    @Column(name = "MEM_ID")
    private Integer memberId; //회원번호 FK

    @Column(name = "HOST_ID")
    private Integer hostId; //숙소번호 FK

    @Column(name = "ROOM_ID")
    private Integer roomId; // 객실번호 FK

    @Column(name = "CHECKIN_DATE")
    private LocalDate checkInDate; // 체크인날짜
    
    @Column(name = "CHECKOUT_DATE")
    private LocalDate checkOutDate; // 체크아웃날짜
    
    @Column(name = "BOOK_STATUS")
    private Integer bookStatus=10; // 예약상태 : default 10(예약완료)
    
    @Column(name = "GUEST_NAME")
    private String guestName; // 예약자명
    
    @Column(name = "GUEST_CONTACT")
    private String guestContact; // 예약자연락처
    
    @Column(name = "PAY_TYPE")
    private Integer payType; // 결제수단 : 무통장입금 10 / 카드 20
    
    @Column(name = "PAY_PRICE")
    private BigDecimal payPrice; // 결제금액
    
    @Column(name = "PAY_DATE")
    private LocalDateTime payDate; // 결제일자
    
    
    @Builder
    public Booking(Integer memberId, Integer hostId, Integer roomId, LocalDate checkInDate, LocalDate checkOutDate,
                   Integer bookStatus, String guestName, String guestContact, Integer payType, BigDecimal payPrice) {
        this.memberId = memberId;
        this.hostId = hostId;
        this.roomId = roomId;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.bookStatus = bookStatus;
        this.guestName = guestName;
        this.guestContact = guestContact;
        this.payType = payType;
        this.payPrice = payPrice;
    }

    @PrePersist
    protected void onCreate() {
        this.payDate = LocalDateTime.now(); // 예약정보 생성 시점에 현재 시간을 payDate로 설정
    }

}