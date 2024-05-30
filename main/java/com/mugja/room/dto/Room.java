package com.mugja.room.dto;

import com.mugja.host.dto.Host;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "ROOM")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ROOM_ID")
    private Integer roomId; // 객실번호 PK

    @ManyToOne
    @JoinColumn(name = "HOST_ID", nullable = false)
    private Host host; // 숙소번호 FK

    @Column(name = "ROOM_MAXCAPACITY", nullable = false)
    private Integer roomMaxCapacity; // 객실정원

    @Column(name = "ROOM_PRICE", nullable = false)
    private BigDecimal roomPrice; // 객실가격

    @Column(name = "ROOM_NAME", nullable = false)
    private String roomName; // 객실이름

    @Column(name = "ROOM_ISBOOKED", nullable = false)
    private boolean roomIsBooked; // 예약가능여부


    @Builder
    public Room(Host host, Integer roomMaxCapacity, BigDecimal roomPrice, String roomName, boolean roomIsBooked) {
        this.host = host;
        this.roomMaxCapacity = roomMaxCapacity;
        this.roomPrice = roomPrice;
        this.roomName = roomName;
        this.roomIsBooked = roomIsBooked;
    }

}
