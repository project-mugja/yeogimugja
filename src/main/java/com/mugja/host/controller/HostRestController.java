package com.mugja.host.controller;

import com.mugja.global.exceptions.HostNotFoundException;
import com.mugja.host.dto.Host;
import com.mugja.host.dto.HostWishDTO;
import com.mugja.host.service.HostService;
import com.mugja.member.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/host")
public class HostRestController {

    private final HostService hostService;
    private final MemberService memberService;

    /*
    OK
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
    OK
    숙소 카테고리 검색 메서드 
    카테고리, 검색어, 페이지넘버 => 호스트목록 페이지 객체
    
    예약 가능 여부, 태그 검색 구현 필요
    
    */
    @GetMapping("/category/{category}/{pageNo}/{search}")
    public ResponseEntity<Page<HostWishDTO>> getHostByCategory(@PathVariable String category, @PathVariable int pageNo, @PathVariable String search){
//        try {
            if(memberService.getMemId() != null){
                System.out.println("authed");
                return new ResponseEntity<Page<HostWishDTO>>(
                        hostService.findHostsAuth(
                                memberService.getMemId(),
                                category,
                                search,
                                PageRequest.of(pageNo-1, 8)
                        ),
                        HttpStatus.OK
                );
            }else {
                System.out.println("not authed");
                return new ResponseEntity<Page<HostWishDTO>>(
                        hostService.findHosts(
                                category,
                                search,
                                PageRequest.of(pageNo-1, 8)
                        ),
                        HttpStatus.OK
                );
            }
//        }catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
    }

}
