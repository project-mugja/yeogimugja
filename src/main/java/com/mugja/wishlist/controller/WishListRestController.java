package com.mugja.wishlist.controller;

import com.mugja.host.dto.Host;
import com.mugja.member.service.MemberService;
import com.mugja.member.service.SecurityService;
import com.mugja.wishlist.dto.Wish;
import com.mugja.wishlist.service.WishListService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/mypage/wish")
public class WishListRestController {

    private WishListService wishListService;
    private SecurityService securityService;
    private MemberService memberService;

    //페이지 넘버와 멤버id를 받아 찜목록 보여줌
    @GetMapping("/{pageNo}")
    public ResponseEntity<Page<Wish>> getWishes(
            @PathVariable int pageNo,
            @PathVariable Integer memId
        ){
        if(!memId.equals(memberService.findByEmail(securityService.userId()))){
            return new ResponseEntity<>(HttpStatus.LOCKED);
        }
        return new ResponseEntity<Page<Wish>>(

                wishListService.findWishes(
                        memId,
                        PageRequest.of(pageNo - 1, 8)
                ),
                HttpStatus.OK
        );  
    }

    //찜목록에 추가
    @PostMapping("/{hostId}")
    public ResponseEntity<Wish> createWish(
            @PathVariable Integer hostId
    ){
        if(securityService.userId() == null){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        int memId =  memberService.findByEmail(securityService.userId());
        wishListService.save(
                Wish.builder()
                        .memId(memId)
                        .host(Host.builder().hostId(hostId).build())
                        .build()
        );
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //찜목록에서 제거
    @DeleteMapping("/{hostId}")
    public ResponseEntity<Wish> delWish(
            @PathVariable Integer hostId
    ){
        if(securityService.userId() == null){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        int memId =  memberService.findByEmail(securityService.userId());
        wishListService.delete(
                Wish.builder()
                        .memId(memId)
                        .host(Host.builder().hostId(hostId).build())
                        .build()
        );
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
