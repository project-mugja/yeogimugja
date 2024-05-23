package com.mugja.wishlist.service;

import com.mugja.wishlist.dto.Wish;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface WishListService {

    public Page<Wish> findWishes(Integer memId, Pageable pageable);

    public void save(Wish wish);

    public void delete(Wish wish);
}
