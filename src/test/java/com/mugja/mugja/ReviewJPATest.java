package com.mugja.mugja;

import com.mugja.MugjaApplication;
import com.mugja.review.domain.ReviewRepository;
import com.mugja.review.dto.Review;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = MugjaApplication.class)
public class ReviewJPATest {

    @Autowired
    private ReviewRepository reviewRepository;

    @Test
    public void testFindByHostId() {
        Page<Review> page = reviewRepository.findByHostIdOrderByWriteDateDesc(1, PageRequest.of(0,3));
        assertTrue(page.getTotalElements() > 0,"total elements is zero");
        assertFalse(page.getTotalElements() == 0 ,"total elements is zero");
        System.out.println(page.getContent().toString());
    }

    @Test
    public void testDeleteByRvId(){
        reviewRepository.deleteByRvId(1);
        reviewRepository.findAll().forEach(System.out::println);
    }
}
