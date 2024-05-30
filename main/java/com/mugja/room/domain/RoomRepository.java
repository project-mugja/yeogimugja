package com.mugja.room.domain;

import com.mugja.room.dto.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
