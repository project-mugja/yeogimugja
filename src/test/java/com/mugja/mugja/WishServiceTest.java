package com.mugja.mugja;

import com.mugja.host.dto.Host;
import com.mugja.wishlist.domain.WishListRepository;
import com.mugja.wishlist.dto.Wish;
import com.mugja.wishlist.service.WishListService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
public class WishServiceTest {

    @Autowired
    private WishListService wishListService;
    @Autowired
    private WishListRepository wishListRepository;

    @Test
    public void test() {
        wishListService.delete(Wish.builder()
                .memId(16).host(Host.builder().hostId(1).build()).build());
        assertTrue(wishListService.isFav(1,16), "삭제 성공");
    }
}
