package com.mugja.host.controller;

import com.mugja.global.exceptions.HostNotFoundException;
import com.mugja.host.dto.Host;
import com.mugja.host.service.HostService;
import com.mugja.member.service.MemberService;
import com.mugja.member.service.MemberServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/host")
public class HostRestController {

    private final HostService hostService;
    private final MemberService memberService;

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

    /*
    숙소 카테고리 검색 메서드

    @GetMapping("/{hostId}/category/{category}")
    public ResponseEntity<Page<Host>> getHostByCategory(){
        return null;
    }
    */

    /*
    찜한 숙소를 카테고리 별로 검색
     */
    @GetMapping("/category/{category}/{pageNo}")
    public ResponseEntity<Page<Host>>getFavHostsByCategory(@PathVariable String category, @PathVariable int pageNo){
        try {
            System.out.println("Category: " + memberService.getMemId());
            return new ResponseEntity<Page<Host>>(hostService.findFavHosts(memberService.getMemId(),category, PageRequest.of(pageNo-1, 8)),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
