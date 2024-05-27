package com.mugja.member.service;

import com.mugja.member.dto.MemberDto;


public interface MemberService {
	
	void createmember(MemberDto dto);
	int searchmember(MemberDto dto);
	void randompwd(MemberDto dto);
	boolean pwdcheck(MemberDto dto);
	void pwdchange(MemberDto dto);
}
