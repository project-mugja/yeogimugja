package com.mugja.room.service;



import com.mugja.room.domain.RoomRepository;
import com.mugja.room.dto.Room;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    @Override
    public List<Room> findRooms(Integer hostId) {
        List<Room> rooms = roomRepository.findByHost_HostIdOrderByPriceDesc(hostId);
        return rooms;
    }
}

