package com.mugja.host.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mugja.host.dto.HostAdminDto;
import com.mugja.host.dto.HostImgDto;
import com.mugja.host.dto.HostTagDto;
import com.mugja.host.mapper.HostAdminMapper;

@Service
public class HostAdminListService {

	
	@Autowired
	private HostAdminMapper mapper;
	
	
	
	public List<HostAdminDto> hostlist() {
		List<HostAdminDto> list = mapper.hostList();
		
		  for(HostAdminDto a : list) {
		  String img = mapper.hostImgList(a.getHost_id()); 
		  if(img==null) {
			  img="default.jpg"; 
			  } 
		  a.setHost_imgpath(img); 
			  
		  }
		
		return list;
	
	}
	public List<HostAdminDto> hostImgList(int hostid) {
		List<HostAdminDto> list = mapper.hostList();
		for(HostAdminDto a : list) {
		
			String img = mapper.hostImgList(a.getHost_id());
			if(img==null) {
				img ="default.png";
			}
			a.setHost_imgpath(img); 
		}
		return list;
	}
	
	//게시글 상세보기 이미지파일
	public List<HostImgDto> hostDetailImg(String hostid) {
		return mapper.hostDetailimg(hostid);
	}
	//게시글 상세보기 내용
	public List<HostAdminDto> hostDetailHost(String hostid) {
		return mapper.hostDetailHost(hostid);
	}
	//게시글 상세보기 태그
	public List<HostTagDto> hostDetailTag(String hostid) {
		
		return mapper.hostDetailTag(hostid);
	}
}
