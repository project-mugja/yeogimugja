package com.mugja.member.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.mugja.member.dto.MemberDto;

@Mapper
public interface MemberMapper {
	int createmember(MemberDto dto);
	int emailserach(String email);
	
	@Select("SELECT * FROM mem WHERE mem_email = #{email}")
	MemberDto findByEmail(String email);
	
}
