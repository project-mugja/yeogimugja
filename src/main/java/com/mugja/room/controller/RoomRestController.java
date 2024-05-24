package com.mugja.room.controller;

import com.mugja.room.dto.Room;
import com.mugja.room.service.RoomService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/host/{hostId}/rooms")
public class RoomRestController {

    private final RoomService roomService;

    @GetMapping("/")
    public ResponseEntity<Page<Room>> getRooms(
            @PathVariable int hostId) {
        return new ResponseEntity<Page<Room>>(roomService.findRooms(hostId, PageRequest.of(0, 1)), HttpStatus.OK);
    }
}
