package com.mugja.mugja;

import com.mugja.booking.dto.Book;
import com.mugja.booking.domain.BookingRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
public class BookTest {

    @Autowired
    BookingRepository bookRepository;

    @Test
    void save() {
        // 예약정보 생성 파라미터 생성
        Book params = Book.builder()
                .memberId(1)
                .hostId(1)
                .roomId(2)
                .checkInDate(LocalDateTime.of(2024, 5, 24, 15, 0))
                .checkOutDate(LocalDateTime.of(2024, 5, 25, 11, 0))
                .guestName("쩨미")
                .guestContact("010-1111-2222")
                .payType(20)
                .payPrice(BigDecimal.valueOf(100000))
                .build();

        // 예약정보 저장
        bookRepository.save(params);
    }

    @Test
    void delete() {
        // 예약정보 조회
        Book entity = bookRepository.findById((long) 2).get();
        // 예약정보 삭제
        bookRepository.delete(entity);
    }

    @Test
    void findAll() {
        // 전체 예약정보 조회
        long bookCount = bookRepository.count();
        // 전체 예약정보 리스트 조회
        List<Book> books = bookRepository.findAll();
    }
}