package com.mugja.review.domain;

import com.mugja.review.dto.Review;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, RVId> {

    //숙소별 리뷰조회
    Page<Review> findByHostIdOrderByWriteDateDesc(Integer hostId, Pageable pageable);

    //리뷰 삭제
    @Transactional
    void deleteByRvId(Integer rvId);
}
