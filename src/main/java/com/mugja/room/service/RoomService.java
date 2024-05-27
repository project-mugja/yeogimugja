package com.mugja.room.service;

import com.mugja.room.dto.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RoomService {

    public List<Room> findRooms(Integer hostId);
}
