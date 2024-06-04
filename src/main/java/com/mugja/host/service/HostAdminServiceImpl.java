package com.mugja.host.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.mugja.host.dto.HostAdminDto;
import com.mugja.host.dto.HostTagDto;
import com.mugja.host.mapper.HostAdminMapper;


@Service
public class HostAdminServiceImpl implements HostAdminService {
	
	@Value("${spring.servlet.multipart.location}")
	private String uploadPath;
	
	@Autowired
	private HostAdminMapper mapper;
	
	
	
	//숙소생성 메서드
	@Transactional
	public int hostRoomWrite(HostAdminDto dto,HostTagDto hashdto,MultipartFile[] files) {
		dto.setHost_address(dto.getHost_address()+" "+dto.getHost_address_detail());
		mapper.hostwrite(dto);
		dto.setHost_id(mapper.gethostId(dto));
		hashdto.setHostId(mapper.gethostId(dto));
		hostImgWrite(dto,files);
		//hostHashAdd(hashdto);
		addTags(hashdto, 
		hashdto.getTag1() != null ? hashdto.getTag1().split(",") : null,
		hashdto.getTag2() != null ? hashdto.getTag2().split(",") : null,
		hashdto.getTag3() != null ? hashdto.getTag3().split(",") : null,
		hashdto.getTag4() != null ? hashdto.getTag4().split(",") : null);
		return dto.getHost_id();
		
	}
	
	//숙소수정 메서드
	@Transactional
	public int hostRoomUpdate(HostAdminDto dto,HostTagDto hashdto,MultipartFile[] files) {
		mapper.hostupdate(dto);
		hashdto.setHostId(dto.getHost_id());
		System.out.println(mapper.gethostId(dto)+"hostId");
	
		
		updateTags(hashdto,
		hashdto.getTag1() != null ? hashdto.getTag1().split(",") : null,
		hashdto.getTag2() != null ? hashdto.getTag2().split(",") : null,
		hashdto.getTag3() != null ? hashdto.getTag3().split(",") : null,
		hashdto.getTag4() != null ? hashdto.getTag4().split(",") : null);
		
		//hostImgWrite(dto,files);
		return dto.getHost_id();
		
	}
	
	
	
	//이미지업로드 설정
	public void hostImgWrite(HostAdminDto dto,MultipartFile[] files) {
		for(MultipartFile file : files) {
			if(!file.isEmpty()&&file.getContentType().startsWith("image")) {
				String orgfile = file.getOriginalFilename();
				String filename = orgfile.substring(orgfile.lastIndexOf("/")+1);
				String storename = UUID.randomUUID().toString()+"_"+filename.substring(filename.lastIndexOf("."));
				try {
					file.transferTo(Paths.get(uploadPath,storename));
					mapper.hostImgWirte(dto,storename);
					
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			
		}
		
	}
	//해시태그 입력
	public void hostHashAdd(HostTagDto hashdto) {
		mapper.hostAddTags(hashdto);
	}
	
    private void addTags(HostTagDto hostTagDto, String[] tag1Array, String[] tag2Array, String[] tag3Array, String[] tag4Array) {
        hostTagDto.setTag1(arrayToString(tag1Array));
        hostTagDto.setTag2(arrayToString(tag2Array));
        hostTagDto.setTag3(arrayToString(tag3Array));
        hostTagDto.setTag4(arrayToString(tag4Array));
        mapper.hostAddTags(hostTagDto);
    }
    
    
    //태그 수정메서드
    private void updateTags(HostTagDto hostTagDto, String[] tag1Array, String[] tag2Array, String[] tag3Array, String[] tag4Array) {
    	hostTagDto.setTag1(arrayToString(tag1Array));
    	hostTagDto.setTag2(arrayToString(tag2Array));
    	hostTagDto.setTag3(arrayToString(tag3Array));
    	hostTagDto.setTag4(arrayToString(tag4Array));
    	mapper.hostUpdateTags(hostTagDto);
    }


    private String arrayToString(String[] array) {
        if (array == null || array.length == 0) {
            return null;
        }
        return Arrays.stream(array)
                     .filter(tag -> tag != null && !tag.isEmpty())
                     .collect(Collectors.joining(","));
    }
	
	
	//폴더생성 설정 (이미지업로드 메서드에서 실행)
	public String makeFolder() {
		
		String str = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
		String folderPath = str;
		
		//make folder 
		File uploadPathFolder = new File(uploadPath,folderPath);
		
		if(uploadPathFolder.exists()==false) {
			uploadPathFolder.mkdirs();
		}
		
		return folderPath;
	}
	
	//게시글 삭제요청
	@Transactional
	public void hostdelete(String hostid) {
		mapper.hostImgdelete(hostid);
		mapper.hostTagdelete(hostid);
		mapper.hostDelete(hostid);
		
	}
	
	
}
