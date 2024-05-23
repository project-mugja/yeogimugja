package com.mugja.wishlist.controller;

import com.mugja.host.dto.Host;
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
@RequestMapping("/mypage/{memId}/wish")
public class WishListRestController {

    private WishListService wishListService;

    @GetMapping("/{pageNo}")
    public ResponseEntity<Page<Wish>> getWishes(
            @PathVariable int pageNo,
            @PathVariable Integer memId
        ){
        return new ResponseEntity<Page<Wish>>(
                wishListService.findWishes(
                        memId,
                        PageRequest.of(pageNo - 1, 8)
                ),
                HttpStatus.OK
        );  
    }

    @PostMapping("/{hostId}")
    public ResponseEntity<Wish> createWish(
            @PathVariable Integer hostId,
            @PathVariable Integer memId
    ){
        wishListService.save(
                Wish.builder()
                        .memId(memId)
                        .host(Host.builder().hostId(hostId).build())
                        .build()
        );
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{hostId}")
    public ResponseEntity<Wish> delWish(
            @PathVariable Integer hostId,
            @PathVariable Integer memId
    ){
        wishListService.delete(
                Wish.builder()
                        .memId(memId)
                        .host(Host.builder().hostId(hostId).build())
                        .build()
        );
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
