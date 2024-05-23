package com.mugja.room.service;

import com.mugja.room.dto.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RoomService {

    public Page<Room> findRooms(Integer hostId,Pageable pageable);
}
