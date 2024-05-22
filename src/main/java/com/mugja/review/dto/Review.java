package com.mugja.review.dto;

import com.mugja.review.domain.RVId;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;


@Getter
@Setter
@Entity
@IdClass(RVId.class)
@Table(name = "rv")
public class Review {
    @Id
    @Column(name = "rv_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer rvId;

    @Id
    @Column(name = "mem_id", nullable = false)
    private Integer memId;

    @Id
    @Column(name = "host_id", nullable = false)
    private Integer hostId;

    @Column(length = 100, name = "rv_content", nullable = false)
    private String content;

    @Column(name = "rv_score", nullable = false)
    private Byte score;

    @Column(name = "rv_writedate", nullable = false)
    private Date writeDate;

    @Column(length = 200, name = "rv_imgpath", nullable = true)
    private String imgPath;

    @Transient
    private MultipartFile image;

    public MultipartFile getImage() {
        return image;
    }
}
