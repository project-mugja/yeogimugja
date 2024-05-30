package com.mugja.host.service;

import com.mugja.global.exceptions.HostNotFoundException;
import com.mugja.host.dto.Host;

public interface HostService {

    public Host findHost(Integer hostId) throws HostNotFoundException;
    //==================================================


}

