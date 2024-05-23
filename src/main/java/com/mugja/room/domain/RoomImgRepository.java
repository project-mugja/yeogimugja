package com.mugja.room.domain;

import com.mugja.room.dto.Room;
import com.mugja.room.dto.RoomImg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomImgRepository extends JpaRepository<RoomImg, Integer> {
    List<RoomImg> findByRoom(Room room);
}
