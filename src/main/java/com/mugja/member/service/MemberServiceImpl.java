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
    @Autowired
    private SecurityService securityService;


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
	
	//랜덤비밀번호 생성후 메일발송
		@Override
		public void randompwd(MemberDto dto) {
			dto.setMem_pwd(bCryptPasswordEncoder.encode(dto.getMem_pwd()));
			System.out.println(dto.getMem_email());
			System.out.println(dto.getMem_pwd());
			mapper.randompwd(dto);
		}
		
		
	@Override
	public int searchmember(MemberDto dto) {
		return mapper.emailserach(dto.getMem_email());
	}


	// memId값을 가져옴
	@Override
	public Integer getMemId(){
		return mapper.findMemId(securityService.userId());
	}

	//현재비밀번호 -입력비밀번호 확인 메서드
	@Override
	public boolean pwdcheck(MemberDto dto) {
		//입력받은 비밀번호
		String pwd = dto.getMem_pwd();
		String pwd_db = mapper.pwdsearch(dto.getMem_email());
		boolean test = bCryptPasswordEncoder.matches(pwd, pwd_db);
		if(test==true) {
			mapper.emailserach(pwd);
		}
		System.out.println(test); 
		return test;
	}
	
	
	@Override
	public void pwdchange(MemberDto dto) {
		dto.setMem_pwd(bCryptPasswordEncoder.encode(dto.getMem_pwd()));
		mapper.randompwd(dto);
	}
	
	
}
