package com.mugja.review.controller;


import com.mugja.review.domain.ReviewRepository;
import com.mugja.review.dto.Review;
import com.mugja.review.service.ReviewServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@AllArgsConstructor
@RequestMapping("/host/{hostId}/review")
public class ReviewController {

    private final ReviewServiceImpl reviewService;
    private final ReviewRepository reviewRepository;

    //댓글 목록 보여주기
    @GetMapping("/{pageNum}")
    public ResponseEntity<Page<Review>> getReviews(
            @PathVariable Integer hostId,
            @PathVariable Integer pageNum
        ) {
        //첫페이지는 0
        return new ResponseEntity<Page<Review>>(reviewService.findReviews(pageNum-1,2,hostId), HttpStatus.OK);
    }

    //댓글 작성 하기
    @PostMapping("/")
    public ResponseEntity<String> doWrite(
            @PathVariable Integer hostId,
            @ModelAttribute Review review
        ){
        try{
            review.setHostId(hostId);
            reviewService.save(review);
            return ResponseEntity.ok().body("Success");
        }catch (IOException e){
            e.printStackTrace();
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
