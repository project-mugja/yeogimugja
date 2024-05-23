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

    @Column(name = "host_contect", nullable = false)
    private String hostContact;

    @Column(name = "host_intro")
    private String hostIntro;

    @Column(name = "host_api", nullable = false)
    private String location;

   @Transient
    private List<HostImg> hostImgs;

    public void setHostImgs(List<HostImg> hostImgs) {
        this.hostImgs = hostImgs;
    }

    @Builder
    public Host(Integer hostId, Byte avgScore, String hostName, String hostAddress, String hostContact, String hostIntro) {
       this.hostId = hostId;
       this.avgScore = avgScore;
       this.hostName = hostName;
       this.hostAddress = hostAddress;
       this.hostContact = hostContact;
       this.hostIntro = hostIntro;
   }
}
