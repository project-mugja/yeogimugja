package com.mugja.host.dto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.apachecommons.CommonsLog;

@Getter
@Setter
@Entity
@Table(name = "host")
public class Host {

    @Id
    @Column(name = "host_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer hostId;

    @Column(name = "avgscore")
    private Byte avgScore;

    @Column(name = "host_name")
    private String hostName;

    @Column(name = "host_address")
    private String hostAddress;

    @Column(name = "host_contect")
    private String hostContact;

    @Column(name = "host_intro")
    private String hostIntro;

    @Column(name = "host_api")
    private String location;
}
