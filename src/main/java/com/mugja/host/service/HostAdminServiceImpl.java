package com.mugja.host.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

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
	
	
	
	@Transactional
	public void hostRoomWrite(HostAdminDto dto,HostTagDto hashdto,MultipartFile[] files) {
		mapper.hostwrite(dto);
		dto.setHost_id(mapper.gethostId(dto));
		hostImgWrite(dto,files);
		hostHashAdd(hashdto);
		
	}
	
	
	
	//이미지업로드 설정
	public void hostImgWrite(HostAdminDto dto,MultipartFile[] files) {
		for(MultipartFile file : files) {
			if(!file.isEmpty()&&file.getContentType().startsWith("image")) {
				String orgfile = file.getOriginalFilename();
				String filename = orgfile.substring(orgfile.lastIndexOf("/")+1);
				String storename = UUID.randomUUID().toString()+"_"+filename.substring(filename.lastIndexOf("."));
				try {
					file.transferTo(Paths.get(uploadPath,makeFolder(),storename));
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
		mapper.hostAddhash(hashdto);
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
}
