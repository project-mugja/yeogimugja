package com.mugja.host.domain;

import com.mugja.host.dto.HostImg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HostImgRepository extends JpaRepository<HostImg, Integer> {
    List<HostImg> findByHost_HostId(Integer hostId);
}
