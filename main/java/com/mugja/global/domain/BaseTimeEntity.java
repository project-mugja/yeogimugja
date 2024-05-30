package com.mugja.global.domain;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass
public abstract class BaseTimeEntity {

    @Column(name = "BOOK_DATE")
    private LocalDateTime bookDate; //예약일시

    @Column(name = "CHECKIN")
    private LocalDateTime checkInDate; //체크인일시

    @Column(name = "CHECKOUT")
    private LocalDateTime checkOutDate; //체크아웃일시

    @Column(name = "RV_WRITEDATE")
    private LocalDateTime rvWriteDate; //리뷰작성일시

    private LocalDateTime rvModifiedDate; //리뷰수정일시

    @PrePersist
    public void prePersist() {
        this.rvWriteDate = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.rvModifiedDate = LocalDateTime.now();
    }

}
