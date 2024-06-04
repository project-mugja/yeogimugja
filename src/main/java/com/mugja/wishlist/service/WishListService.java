package com.mugja.wishlist.service;

import com.mugja.wishlist.dto.Wish;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface WishListService {

    Page<Wish> findWishes(Integer memId, Pageable pageable);

    void save(Wish wish);

    void delete(Wish wish);

    Boolean isFav(Integer hostId, Integer memId);
}
