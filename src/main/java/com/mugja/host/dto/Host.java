package com.mugja.host.dto;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.apachecommons.CommonsLog;

import java.util.List;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "host")
public class Host {

    @Id
    @Column(name = "host_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer hostId;

    @Column(name = "avgscore")
    private Byte avgScore;

    @Column(name = "host_name", nullable = false)
    private String hostName;

    @Column(name = "host_address", nullable = false)
    private String hostAddress;

    @Column(name = "host_contact", nullable = false)
    private String hostContact;

    @Column(name = "host_intro")
    private String hostIntro;

    @Column(name = "host_lat", nullable = false)
    private Double lat;

    @Column(name = "host_lng", nullable = false)
    private Double lng;

    @OneToMany(mappedBy = "host", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HostImg> hostImgList;

    @Builder
    public Host(Integer hostId, Byte avgScore, String hostName, String hostAddress, String hostContact, String hostIntro, Double lat, Double lng) {
       this.hostId = hostId;
       this.avgScore = avgScore;
       this.hostName = hostName;
       this.hostAddress = hostAddress;
       this.hostContact = hostContact;
       this.hostIntro = hostIntro;
       this.lat = lat;
       this.lng = lng;
   }
}
