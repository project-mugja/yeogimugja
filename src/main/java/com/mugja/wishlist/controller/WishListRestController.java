package com.mugja.wishlist.controller;

import com.mugja.host.dto.Host;
import com.mugja.member.service.MemberService;
import com.mugja.member.service.SecurityService;
import com.mugja.wishlist.dto.Wish;
import com.mugja.wishlist.service.WishListService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/mypage/wish")
public class WishListRestController {

    private WishListService wishListService;
    private SecurityService securityService;
    private MemberService memberService;

    //OK
    //페이지 넘버와 멤버id를 받아 위시리스트목록 보여줌
    @GetMapping("/{pageNo}")
    public ResponseEntity<Page<Wish>> getWishes(
            @PathVariable int pageNo
        ){
        if(memberService.getMemId() == null){
            return new ResponseEntity<>(HttpStatus.LOCKED);
        }
        return new ResponseEntity<Page<Wish>>(
                wishListService.findWishes(
                        memberService.getMemId()
                        ,
                        PageRequest.of(pageNo - 1, 8)
                ),
                HttpStatus.OK
        );  
    }

    //OK
    //유저가 이 숙소에 찜하기를 하였는가?
    @GetMapping("/{hostId}/")
    public ResponseEntity<Boolean> isFav(@PathVariable int hostId){
        System.out.println("memId : "+memberService.getMemId());
        try {
            return new ResponseEntity<>(wishListService.isFav(hostId, memberService.getMemId()),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    //wishlist에 추가
    @PostMapping("/{hostId}")
    public ResponseEntity<Wish> createWish(
            @PathVariable Integer hostId
    ){
        try{
            wishListService.save(
                    Wish.builder()
                            .memId(memberService.getMemId())
                            .host(Host.builder().hostId(hostId).build())
                            .build()
            );
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    //wishlist에서 제거
    @DeleteMapping("/{hostId}")
    public ResponseEntity<Wish> delWish(
            @PathVariable Integer hostId
    ){
        try {
            wishListService.delete(
                    Wish.builder()
                            .memId(memberService.getMemId())
                            .host(Host.builder().hostId(hostId).build())
                            .build()
            );
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

}
