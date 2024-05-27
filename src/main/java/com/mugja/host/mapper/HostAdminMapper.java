package com.mugja.host.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.mugja.host.dto.HostAdminDto;
import com.mugja.host.dto.HostTagDto;

@Mapper
public interface HostAdminMapper {
	
	void hostwrite(HostAdminDto dto);
	int gethostId(HostAdminDto dto);
	
	@Insert("INSERT INTO host_img VALUES (null,#{dto.host_id}, #{host_imgpath})")
	void hostImgWirte(@Param("dto") HostAdminDto dto,@Param("host_imgpath") String storename);
	
	
	void hostAddhash(HostTagDto dto);
	
}
