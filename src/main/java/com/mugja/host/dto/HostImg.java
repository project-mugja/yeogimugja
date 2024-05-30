package com.mugja.host.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @Column(name = "host_imgpath",nullable = false)
    private String imgPath;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "host_id")
    @JsonIgnore
    private Host host;
}
