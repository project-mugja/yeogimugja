package com.mugja.room.domain;

import com.mugja.host.dto.Host;
import com.mugja.room.dto.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {
    Page<Room> findByHost_HostIdOrderByPriceDesc(Integer hostId, Pageable pageable);
}
