package com.mugja.wishlist.service;

import com.mugja.wishlist.domain.WishListRepository;
import com.mugja.wishlist.dto.Wish;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class WishListServiceImpl implements WishListService {

    private final WishListRepository wishListRepository;

    @Override
    public Page<Wish> findWishes(Integer memId, Pageable pageable) {
        return wishListRepository.findByMemId(memId,pageable);
    }

    @Override
    @Transactional
    public void save(Wish wish) {
        if(!isFav(wish.getHost().getHostId(), wish.getMemId())){
            wishListRepository.save(wish);
        }
    }

    @Override
    @Transactional
    public void delete(Wish wish) {
        if(isFav(wish.getHost().getHostId(), wish.getMemId())){
            wishListRepository.deleteByMemIdAndHost_HostId(wish.getMemId(),wish.getHost().getHostId());
        }
    }

    @Override
    public Boolean isFav(Integer hostId, Integer memId) {
        return wishListRepository.findByMemIdAndHost_HostId(memId, hostId).isPresent();
    }


}
