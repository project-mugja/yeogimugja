package com.mugja.host.service;

import com.mugja.global.exceptions.HostNotFoundException;
import com.mugja.host.domain.HostImgRepository;
import com.mugja.host.domain.HostRepository;
import com.mugja.host.dto.Host;
import com.mugja.host.dto.HostWishDTO;
import com.mugja.room.domain.RoomRepository;
import com.mugja.wishlist.domain.WishListRepository;
import com.mugja.wishlist.service.WishListService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;



@Service
public class HostServiceImpl implements HostService{

    private final HostRepository hostRepository;
    private final HostImgRepository hostImgRepository;
    private final WishListRepository wishListRepository;
    private final RoomRepository roomRepository;

    @Autowired
    public HostServiceImpl(HostRepository hostRepository, HostImgRepository hostImgRepository, WishListRepository wishListRepository, RoomRepository roomRepository) {
        this.hostRepository = hostRepository;
        this.hostImgRepository = hostImgRepository;
        this.wishListRepository = wishListRepository;
        this.roomRepository = roomRepository;
    }

    /*
    hostId로 숙소 정보와 그 숙소의 이미지를 불러옴
     */
    @Override
    @Transactional
    public Host findHost(Integer hostId) throws HostNotFoundException {
        System.out.println("findhost");
        //숙소 정보 가져오기
        Host host = hostRepository
                .findByHostId(hostId)
                .orElseThrow(() -> new HostNotFoundException());

        return host;
    }

//    @Override
//    @Transactional
//    public Page<Host> findFavHosts(Integer memId,String category,Pageable pageable) {
//        return hostRepository.findHostByTagAndFavoriteNative(memId,category,pageable);
//    }

    //로그인 상태에서 숙소 검색(찜한 숙소 정보도 같이 가져옴)
    @Override
    @Transactional
    public Page<HostWishDTO> findHostsAuth(Integer memId, String category, String search, Pageable pageable) {
        System.out.println("search service : "+search);
        Page<HostWishDTO> page = hostRepository.findByTagNative(category, search, pageable);
        System.out.println("findHostsAuth");
        page.forEach(hostWishDTO -> {
            hostWishDTO.setHostImgList(
                    hostImgRepository.findAllByHost_HostId(hostWishDTO.getHostId())
            );
            hostWishDTO.setIsFav(
                    wishListRepository.findByMemIdAndHost_HostId(memId, hostWishDTO.getHostId()).isPresent()
            );
            System.out.println("isFav = " + hostWishDTO.isFav());
            hostWishDTO.setPrice(
                    roomRepository.findByHost_HostIdOrderByPriceAsc(hostWishDTO.getHostId()).get(0).getPrice()
            );
        });
        return page;
    }

    //비로그인 상태에서 숙소 검색
    @Override
    @Transactional
    public Page<HostWishDTO> findHosts(String category, String search, Pageable pageable) {
        System.out.println("search service : "+search);
        Page<HostWishDTO> page = hostRepository.findByTagNative(category, search, pageable);
        page.forEach(hostWishDTO -> {
            hostWishDTO.setHostImgList(
                    hostImgRepository.findAllByHost_HostId(hostWishDTO.getHostId())
            );
            hostWishDTO.setPrice(
                    roomRepository.findByHost_HostIdOrderByPriceAsc(hostWishDTO.getHostId()).get(0).getPrice()
            );
        });
        return page;
    }

}
