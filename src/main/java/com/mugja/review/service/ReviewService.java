package com.mugja.review.service;

import com.mugja.review.dto.Review;
import org.springframework.data.domain.Page;

import java.io.IOException;

public interface ReviewService {

    public Page<Review> findReviews(int page, int size, Integer hostId);

    public Review save(Review review) throws IOException;

    public void delete(Integer rvId, Integer memId) throws IOException;
}
