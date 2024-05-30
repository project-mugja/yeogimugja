package com.mugja.host.domain;

import com.mugja.host.dto.HostWishDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface HostRepositoryCustom {

    //숙소 검색
    Page<HostWishDTO> findByTagNative(String category, String search,Pageable pageable);
}
