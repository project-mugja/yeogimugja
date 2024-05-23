package com.mugja.room.service;


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
    private final RoomImgRepository roomImgRepository;

    @Override
    public Page<Room> findRooms(Integer hostId, Pageable pageable) {
        Page<Room> rooms = roomRepository.findByHostIdOrderByPriceDsc(hostId,pageable);
        rooms.forEach(room -> {
            room.setImages(
                    roomImgRepository.findByRoom(room)
            );
        });
        return rooms;
    }
}
