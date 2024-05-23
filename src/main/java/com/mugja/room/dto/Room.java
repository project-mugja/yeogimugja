package com.mugja.room.dto;

import com.mugja.host.dto.Host;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "room")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id", nullable = false)
    private Integer roomId;

//    @Column(name = "host_id", nullable = false)
//    private Integer hostId;
    @ManyToOne
    @JoinColumn(name = "host_id", nullable = false)
    private Host host;

    @Column(name = "room_capacity", nullable = false)
    private Byte capacity;

    @Column(name = "room_price", nullable = false)
    private Integer price;

    @Column(name = "room_name", nullable = false)
    private String name;

    @Column(name = "room_status", nullable = false)
    private Boolean status;

    @Transient
    List<RoomImg> images;
}
