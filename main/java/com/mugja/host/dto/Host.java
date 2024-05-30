package com.mugja.host.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "HOST")
public class Host {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "HOST_ID")
    private Integer hostId; // 숙소번호 PK

    @Column(name = "AVGSCORE")
    private Integer avgScore; // 숙소평점

    @Column(name = "HOST_NAME", nullable = false)
    private String hostName; // 숙소이름

    @Column(name = "HOST_ADDRESS", nullable = false)
    private String hostAddress; // 숙소주소

    @Column(name = "HOST_CONTACT", nullable = false)
    private String hostContact; // 숙소연락처

    @Column(name = "HOST_INTRO", nullable = false)
    private String hostIntro; // 숙소소개글

    @Column(name = "HOST_LAT", nullable = false)
    private float hostLat; // 숙소 위도

    @Column(name = "HOST_LNG", nullable = false)
    private float hostLng; // 숙소 경도


    @Builder
    public Host(Integer avgScore, String hostName, String hostAddress, String hostContact, String hostIntro, float hostLat, float hostLng) {
        this.avgScore = avgScore;
        this.hostName = hostName;
        this.hostAddress = hostAddress;
        this.hostContact = hostContact;
        this.hostIntro = hostIntro;
        this.hostLat = hostLat;
        this.hostLng = hostLng;
    }

}
