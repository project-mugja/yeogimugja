package com.mugja.host.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Builder
public class HostWishDTO {
    private Integer hostId;
    private Byte avgScore;
    private String hostName;
    private String hostAddress;
    private List<HostImg> hostImgList;
    private Integer wishId;
    private String category;
}
