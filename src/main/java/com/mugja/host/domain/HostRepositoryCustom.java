package com.mugja.host.domain;

import com.mugja.host.dto.HostWishDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface HostRepositoryCustom {
    Page<HostWishDTO> findByTagNative(String category, String search,Pageable pageable);
}
