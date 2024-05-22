package com.mugja.review.service;

import com.mugja.review.dto.Review;
import com.mugja.review.domain.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class ReviewServiceImpl implements ReviewService{

    private final String UPLOAD_DIR = "resources/static/img/reviewimgs";

    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    public Page<Review> findReviews(int page, int size, Integer hostId) {
        Page<Review> reviewPage = reviewRepository.findByHostIdOrderByWriteDateDesc(
                hostId,
                PageRequest.of(page, size)
        );
        return reviewPage;
    }

    @Override
    public Review save(Review review) throws IOException {

        if(review.getImage() != null && !review.getImage().isEmpty()) {
            String imageName = UUID.randomUUID().toString()+review.getWriteDate().toString();
            File dest = new File(UPLOAD_DIR+imageName);
            review.getImage().transferTo(dest);
            review.setImgPath(UPLOAD_DIR+imageName);
        }
        reviewRepository.save(review);
        return review;
    }

    @Override
    public void delete(Integer rvId, Integer memId) throws IOException {
        if(rvId==null || memId==null) {
            throw new IllegalArgumentException("rvId and memId must not be null");
        }

        if(rvId.equals(memId)){
            reviewRepository.deleteByRvId(rvId);
        }else{
            throw new IllegalArgumentException("rvId and memId must match");
        }
    }
}
