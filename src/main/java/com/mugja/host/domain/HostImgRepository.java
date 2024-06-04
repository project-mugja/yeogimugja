package com.mugja.host.domain;

import com.mugja.host.dto.HostImg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HostImgRepository extends JpaRepository<HostImg, Integer> {

    //호스트 아이디로 호스트의 이미지 목록 불러오기
    List<HostImg> findAllByHost_HostId(Integer hostId);
}
