package com.mugja.room.service;


import com.mugja.host.dto.Host;
import com.mugja.room.domain.RoomImgRepository;
import com.mugja.room.domain.RoomRepository;
import com.mugja.room.dto.Room;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    @Override
    public Page<Room> findRooms(Integer hostId, Pageable pageable) {
        Page<Room> rooms = roomRepository.findByHost_HostIdOrderByPriceDesc(hostId,pageable);

        return rooms;
    }
}
