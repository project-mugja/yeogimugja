package com.mugja.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.mugja.member.dto.MemberDto;
import com.mugja.member.mapper.MemberMapper;


@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberMapper mapper;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	public MemberServiceImpl(MemberMapper mapper) {
	        this.mapper = mapper;
	    }
	

	@Override
	public void createmember(MemberDto dto) {
		
		if(mapper.emailserach(dto.getMem_email())>0) {
			return;
		}
		dto.setMem_pwd(bCryptPasswordEncoder.encode(dto.getMem_pwd()));
		dto.setMem_grade("ROLE_USER");
		mapper.createmember(dto);
	}
	
	@Override
	public int searchmember(MemberDto dto) {
		return mapper.emailserach(dto.getMem_email());
	}
	
	
}
