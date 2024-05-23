package com.mugja.wishlist.dto;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
@Table(name = "wish")
public class Wish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wish_id", nullable = false)
    private Integer wishId;

    @Column(name = "mem_id", nullable = false)
    private Integer memId;

    @ManyToOne
    @JoinColumn(name = "host_id", nullable = false)
    private Host host;

    @Builder
    public Wish(Integer wishId, Integer memId, Host host) {
        this.wishId = wishId;
        this.memId = memId;
        this.host = host;
    }
}
