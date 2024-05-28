package com.mugja.member.service;

import com.mugja.member.dto.MemberDto;


public interface MemberService {
	
	void createmember(MemberDto dto);
	int searchmember(MemberDto dto);
	Integer findByEmail(String email);
	Integer getMemId();
}
