package com.mugja.host.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.mugja.host.dto.HostAdminDto;
import com.mugja.host.dto.HostImgDto;
import com.mugja.host.dto.HostTagDto;

@Mapper
public interface HostAdminMapper {
	
	//숙소생성 
	void hostwrite(HostAdminDto dto);
	//숙소 수정
	void hostupdate(HostAdminDto dto);
	
	
	int gethostId(HostAdminDto dto);
	
	@Insert("INSERT INTO host_img VALUES (null,#{dto.host_id},#{host_imgpath},'null')")
	void hostImgWirte(@Param("dto") HostAdminDto dto,@Param("host_imgpath") String storename);
	
	void hostAddTags(HostTagDto dto);
	
	void hostUpdateTags(HostTagDto hostTagDto);

	
	List<HostAdminDto> hostList();
	String hostImgList(int hostId);
	
	List<HostImgDto> hostDetailimg(String hostid);
	List<HostAdminDto> hostDetailHost(String hostid);
	List<HostTagDto> hostDetailTag(String hostid);
	
	void hostImgdelete(String hostid);
	void hostTagdelete(String hostid);
	void hostDelete(String hostid);
	
}
