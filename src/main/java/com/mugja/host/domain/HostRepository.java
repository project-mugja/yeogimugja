package com.mugja.host.domain;

import com.mugja.host.dto.Host;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HostRepository extends JpaRepository<Host, Integer> {

}
