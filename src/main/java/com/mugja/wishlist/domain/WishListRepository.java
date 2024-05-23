package com.mugja.wishlist.domain;

import com.mugja.wishlist.dto.Wish;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishListRepository extends JpaRepository<Wish, Integer> {
    Page<Wish> findByMemId(Integer memId, Pageable pageable);
}
