package com.mugja.host.service;

import com.mugja.global.exceptions.HostNotFoundException;
import com.mugja.host.domain.HostImgRepository;
import com.mugja.host.domain.HostRepository;
import com.mugja.host.dto.Host;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HostServiceImpl implements HostService{


    private final HostRepository hostRepository;
    private final HostImgRepository hostImgRepository;

    @Autowired
    public HostServiceImpl(HostRepository hostRepository, HostImgRepository hostImgRepository) {
        this.hostRepository = hostRepository;
        this.hostImgRepository = hostImgRepository;
    }

    /*
    hostId로 숙소 정보와 그 숙소의 이미지를 불러옴
     */
    @Override
    @Transactional
    public Host findHost(Integer hostId) throws HostNotFoundException {
        
        //숙소 정보 가져오기
        Host host = hostRepository
                .findByHostId(hostId)
                .orElseThrow(() -> new HostNotFoundException());

        return host;
    }
}
