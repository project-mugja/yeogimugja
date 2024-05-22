package com.mugja.mugja;
import static org.junit.jupiter.api.Assertions.*;

import com.mugja.MugjaApplication;
import com.mugja.review.domain.ReviewRepository;
import com.mugja.review.dto.Review;
import com.mugja.review.service.ReviewService;
import com.mugja.review.service.ReviewServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

import java.io.IOException;
import java.util.Date;

@SpringBootTest(classes = MugjaApplication.class)
public class ReviewServiceImplTest {

    @Autowired
    private ReviewServiceImpl reviewService;

    @Autowired
    ReviewRepository reviewRepository;

    @Test
    public void saveTest(){
        Review review = new Review();
        review.setHostId(10);
        review.setMemId(2);
        review.setContent("asdf");
        review.setScore(Byte.valueOf((byte) 5));
        review.setWriteDate(new Date());

        try {
            reviewService.save(review);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Assertions.assertEquals(
                review,
                reviewRepository.findByHostIdOrderByWriteDateDesc(10, PageRequest.of(0,1))
        );

    }

}
