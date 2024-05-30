package com.mugja.host.domain;

import com.mugja.host.dto.Host;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HostRepository extends JpaRepository<Host, Long> {

}
