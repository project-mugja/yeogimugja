package com.mugja.member.mapper;


import com.mugja.member.dto.LoginRequest;
import com.mugja.member.dto.MemberDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MemberMapper {
	int createmember(MemberDto dto);
	int emailserach(String email);
	void randompwd(MemberDto dto);
	String pwdsearch(String email);
	void pwdchange(String email);
	
	@Select("SELECT * FROM mem WHERE mem_email = #{email}")
	MemberDto findByEmail(String email);

	@Select("SELECT mem_id from mem where mem_email = #{email}")
	Integer findMemId(String email);

	@Select("SELECT count(*) from mem where mem_")
	Integer findMem(LoginRequest loginRequest);

	@Select("SELECT mem_email FROM mem WHERE mem_id = #{memId}")
	String findMemEmail(Integer memId);
}
