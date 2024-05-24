package com.mugja.host.controller;

import com.mugja.global.exceptions.HostNotFoundException;
import com.mugja.host.dto.Host;
import com.mugja.host.service.HostService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/host")
public class HostRestController {

    private final HostService hostService;

    /*
    숙소 id를 받아 숙소 정보를 가져오는 메서드
     */
    @GetMapping("/{hostId}")
    public ResponseEntity<Host>getHostInfo(@PathVariable Integer hostId){
        try {
            return new ResponseEntity<Host>(hostService.findHost(hostId),HttpStatus.OK);
        } catch (HostNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
