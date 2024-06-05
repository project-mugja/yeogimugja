package com.mugja.room.service;


import com.mugja.host.dto.Host;
import com.mugja.host.dto.HostAdminDto;
import com.mugja.host.dto.HostTagDto;
import com.mugja.room.domain.RoomImgRepository;
import com.mugja.room.domain.RoomRepository;
import com.mugja.room.dto.Room;
import com.mugja.room.dto.RoomDto;
import com.mugja.room.mapper.RoomMapper;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
public class RoomServiceImpl implements RoomService {

	private final RoomMapper mapper;
    private final RoomRepository roomRepository;



	//객실생성 메서드
	@Transactional
	public void hostRoomWrite(RoomDto dto,MultipartFile[] files) {
		
		//객실생성 매퍼
		mapper.roomwrite(dto);
		dto.setRoom_id(mapper.roomid(dto));
		hostImgWrite(dto,files);
	}
	
	//객실수정 메서드 -미구현
	@Transactional
	public int hostRoomUpdate(RoomDto dto,MultipartFile[] files) {
//		mapper.hostupdate(dto);
//		hashdto.setHostId(dto.getHost_id());
//		System.out.println(mapper.gethostId(dto)+"hostId");
		
		//hostImgWrite(dto,files);
//		return dto.getHost_id();
		return 1;
		
	}
	
	
	
	//이미지업로드 설정
	public void hostImgWrite(RoomDto dto,MultipartFile[] files) {
		for(MultipartFile file : files) {
			if(!file.isEmpty()&&file.getContentType().startsWith("image")) {
				String orgfile = file.getOriginalFilename();
				String filename = orgfile.substring(orgfile.lastIndexOf("/")+1);
				String storename = UUID.randomUUID().toString()+"_"+filename.substring(filename.lastIndexOf("."));
				try {
					makeFolder();
					file.transferTo(Paths.get("C:\\yogimugja\\img\\room",storename));
					System.out.println(dto.getRoom_id());
					System.out.println(storename);
					mapper.hostRoomImgWrite(dto,storename);
					
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			
		}
		
	}
	
	//폴더생성 설정 (이미지업로드 메서드에서 실행)
	public void makeFolder() {
		
//		String str = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
//		String folderPath = str;
		
		//make folder 
		File uploadPathFolder = new File("C:\\yogimugja\\img\\room");
		
		if(uploadPathFolder.exists()==false) {
			uploadPathFolder.mkdirs();
		}
		
//		return folderPath;
	}
	
	//게시글 삭제요청
		@Transactional
		public void hostdelete(String roomid) {
//			mapper.hostImgdelete(hostid);
//			mapper.hostTagdelete(hostid);
//			mapper.hostDelete(hostid);
			
		}
		
    public List<RoomDto> roomlist(int hostid){
    	List<RoomDto> list = mapper.roomList(hostid);
    	
    	  for(RoomDto a : list) {
    		  
    		  String img = mapper.roomImgList(a.getRoom_id()); 
    		  if(img==null) {
    			  img="default.jpg"; 
    			  } 
    		  a.setRoom_imgpath(img); 
    			  
    		  }
    	return list;
    }
    public String hostname(int hostid) {
    	return mapper.hostname(hostid);
    }
    
    @Override
    public List<Room> findRooms(Integer hostId) {
        List<Room> rooms = roomRepository.findByHost_HostIdOrderByPriceDesc(hostId);
        return rooms;
    }
}


