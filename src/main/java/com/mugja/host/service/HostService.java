package com.mugja.host.service;

import com.mugja.global.exceptions.HostNotFoundException;
import com.mugja.host.dto.Host;
import com.mugja.host.dto.HostWishDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface HostService {

    Host findHost(Integer hostId) throws HostNotFoundException;
    Page<Host> findFavHosts(Integer memId, String category, Pageable pageable);
    Page<HostWishDTO> findHostsAuth(Integer memId, String category, Pageable pageable);
    Page<HostWishDTO> findHosts(String category, Pageable pageable);

    //==================================================


}
