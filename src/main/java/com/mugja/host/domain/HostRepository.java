package com.mugja.host.domain;

import com.mugja.host.dto.Host;
import com.mugja.host.dto.HostWishDTO;
import jakarta.persistence.SqlResultSetMapping;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HostRepository extends JpaRepository<Host, Integer>, HostRepositoryCustom {

    Optional<Host> findByHostId(Integer hostId);

    @Query(value = "SELECT * FROM host WHERE host_id IN (SELECT host_id FROM wish WHERE mem_id = :memId) AND host_id IN (SELECT host_id FROM tag WHERE tag1 = :cat)", nativeQuery = true)
    Page<Host> findHostByTagAndFavoriteNative(@Param("memId") Integer memId, @Param("cat") String cat, Pageable pageable);

}
