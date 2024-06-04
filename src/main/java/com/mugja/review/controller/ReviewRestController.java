package com.mugja.review.controller;


import com.mugja.member.dto.MemberDto;
import com.mugja.member.service.MemberService;
import com.mugja.member.service.SecurityService;
import com.mugja.review.dto.Review;
import com.mugja.review.service.ReviewService;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@AllArgsConstructor
@RequestMapping("/api/host/{hostId}/review")
public class ReviewRestController {

    private final ReviewService reviewService;
    private final SecurityService securityService;
    private final MemberService memberService;

    //OK
    //댓글 목록 보여주기
    @GetMapping("/{pageNum}")
    public ResponseEntity<Page<Review>> getReviews(
            @PathVariable Integer hostId,
            @PathVariable Integer pageNum
        ) {
        //첫페이지는 0
        return new ResponseEntity<Page<Review>>(reviewService.findReviews(pageNum-1,2,hostId), HttpStatus.OK);
    }

    //OK
    //댓글 작성 하기
    @PostMapping("/")
    @Transactional
    public ResponseEntity<String> doWrite(
            @PathVariable Integer hostId,
            @ModelAttribute Review review
        ){
        if(securityService.userId() == null){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        try{
            review.setHostId(hostId);
            review .setMemId(memberService.getMemId());
            reviewService.save(review);
            return ResponseEntity.ok().body("Success");
        }catch (IOException e){
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //댓글 삭제하기
    @DeleteMapping("/{pageNum}/{rvId}")
    public ResponseEntity<String> deleteReview(
            @PathVariable Integer rvId
        ){
        return new ResponseEntity<>("delete", HttpStatus.OK);
    }

    //댓글 수정하기
}
