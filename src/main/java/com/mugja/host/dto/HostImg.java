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
    @Column(name = "host_img_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer hostImgId;

    @Column(name = "host_id")
    private Integer hostId;

    @Column(name = "host_imgpath")
    private String ImgPath;

    @ManyToOne
    @JoinColumn(name = "host_id")
    private Host host;
}
