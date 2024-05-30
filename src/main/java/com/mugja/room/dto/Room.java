package com.mugja.room.dto;

import com.mugja.host.dto.Host;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Entity
@Table(name = "room")
@NoArgsConstructor
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ROOM_ID")
    private Integer roomId; // 객실번호 PK

    @ManyToOne
    @JoinColumn(name = "HOST_ID", nullable = false)
    private Host host; // 숙소번호 FK

    @Column(name = "ROOM_MAXCAPACITY", nullable = false)
    private Integer capacity; // 객실정원

    @Column(name = "ROOM_PRICE", nullable = false)
    private BigDecimal price; // 객실가격

    @Column(name = "ROOM_NAME", nullable = false)
    private String name; // 객실이름

    @Column(name = "ROOM_ISBOOKED", nullable = false)
    private boolean status; // 예약가능여부

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RoomImg> roomImgList;
  
  
    @Builder
    public Room(Host host, Integer roomMaxCapacity, BigDecimal roomPrice, String roomName, boolean roomIsBooked) {
        this.host = host;
        this.capacity = roomMaxCapacity;
        this.price = roomPrice;
        this.name = roomName;
        this.status = roomIsBooked;
    }
}

