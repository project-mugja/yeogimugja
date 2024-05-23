package com.mugja.review.service;

import com.mugja.review.dto.Review;
import com.mugja.review.domain.ReviewRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Service
public class ReviewServiceImpl implements ReviewService{

    private final String UPLOAD_DIR = "C:/ymUpload/reviewImg";
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

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
    @Transactional
    public Review save(Review review) throws IOException {
        review.setWriteDate(new Date());
        if(review.getImage() != null && !review.getImage().isEmpty()) {
            String ogName = review.getImage().getOriginalFilename();
            String imageName = UUID.randomUUID().toString()+sdf.format(new Date())+ogName;
            File file = new File(UPLOAD_DIR,imageName);

            if (!file.exists()) {
                file.mkdirs();
            }

            review.getImage().transferTo(file);
            review.setImgPath(UPLOAD_DIR + imageName);
        }
        reviewRepository.save(review);
        return review;
    }

    @Override
    @Transactional
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
