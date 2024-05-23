package com.mugja.host.dto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "host_img")
public class HostImg {

    @Id
    @Column(name = "host_img_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer hostImgId;

//    @Column(name = "host_id", nullable = false)
//    private Integer hostId;

    @Column(name = "host_imgpath",nullable = false)
    private String ImgPath;

    @ManyToOne
    @JoinColumn(name = "host_id", nullable = false)
    private Host host;
}
