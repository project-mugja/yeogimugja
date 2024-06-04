package com.mugja.host.dto;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;


@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HostWishDTO {
    private Integer hostId;
    private Byte avgScore;
    private String hostName;
    private String hostAddress;
    private List<HostImg> hostImgList;
    private boolean isFav;
    private String category;
    private BigDecimal price;

    public void setHostImgList(List<HostImg> hostImgList) {
        this.hostImgList = hostImgList;
    }

    public void setIsFav(boolean isFav) {
        this.isFav = isFav;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
