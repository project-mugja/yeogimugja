package com.mugja.mugja;

import com.mugja.host.domain.HostImgRepository;
import com.mugja.host.domain.HostRepository;
import com.mugja.host.dto.Host;
import com.mugja.host.service.HostServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class HostServiceTest {

    @Autowired
    private HostRepository hostRepository;

    @Autowired
    private HostImgRepository hostImgRepository;

    @Autowired
    HostServiceImpl hostService;

    @Test
    public void findHostTest(){
        try {
            Host host = hostService.findHost(1);
            System.out.println(host.getHostImgs().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
