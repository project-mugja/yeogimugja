package com.mugja.wishlist.service;

import com.mugja.wishlist.domain.WishListRepository;
import com.mugja.wishlist.dto.Wish;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class WishListServiceImpl implements WishListService {

    private final WishListRepository wishListRepository;

    @Override
    public Page<Wish> findWishes(Integer memId, Pageable pageable) {
        return wishListRepository.findByMemId(memId,pageable);
    }

    @Override
    public void save(Wish wish) {
        wishListRepository.save(wish);
    }

    @Override
    public void delete(Wish wish) {
        wishListRepository.delete(wish);
    }
}
