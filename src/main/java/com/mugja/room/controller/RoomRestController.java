package com.mugja.room.controller;

import com.mugja.room.dto.Room;
import com.mugja.room.service.RoomService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/host/{hostId}/rooms")
public class RoomRestController {

    private final RoomService roomService;

    //OK
    //객실 목록 불러오기
    @GetMapping("/")
    public ResponseEntity<List<Room>> getRooms(
            @PathVariable int hostId) {
        return new ResponseEntity<List<Room>>(roomService.findRooms(hostId), HttpStatus.OK);
    }
}
