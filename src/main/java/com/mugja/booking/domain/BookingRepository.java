package com.mugja.booking.domain;

import com.mugja.booking.dto.Booking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    Page<Booking> findByMemberIdOrderByPayDateDesc(Integer memberId, Pageable pageable);
}

