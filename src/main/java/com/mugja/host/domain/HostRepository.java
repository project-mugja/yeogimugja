package com.mugja.host.domain;

import com.mugja.host.dto.Host;
import com.mugja.host.dto.HostWishDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HostRepository extends JpaRepository<Host, Integer> {
    Optional<Host> findByHostId(Integer hostId);

    @Query(value = "SELECT * FROM host WHERE host_id IN (SELECT host_id FROM wish WHERE mem_id = :memId) AND host_id IN (SELECT host_id FROM tag WHERE tag1 = :cat)", nativeQuery = true)
    Page<Host> findHostByTagAndFavoriteNative(@Param("memId") Integer memId, @Param("cat") String cat, Pageable pageable);

    @Query(value = "select a.host_id, a.avgscore, a.host_name, a.host_address, b.wish_id, c.tag1 from host a left join wish b on a.host_id = b.host_id left join tag c on a.host_id = c.host_id where b.mem_id = :memId and c.tag1 = :category", nativeQuery = true)
    Page<HostWishDTO> findByTagAuthNative(@Param("memId") Integer memId, @Param("category") String category, Pageable pageable);

    @Query(value = "select a.host_id, a.avgscore, a.host_name, a.host_address, c.tag1 from host a left join tag c on a.host_id = c.host_id where and c.tag1 = :category", nativeQuery = true)
    Page<HostWishDTO> findByTagNative(@Param("category") String category, Pageable pageable);
}
