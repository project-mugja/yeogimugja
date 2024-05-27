package com.mugja.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mugja.member.dto.MemberDto;
import com.mugja.member.service.MailService;
import com.mugja.member.service.MemberServiceImpl;
import com.mugja.member.service.SecurityService;

@RestController
@RequestMapping("/mugja")
public class MemberRestController {
	
	@Autowired
	private MailService service;
	@Autowired
	private MemberServiceImpl memberService;
	@Autowired
	private SecurityService securityservice;
	
	private String number="";
	
	@PostMapping("/email")
	public boolean email(@RequestBody MemberDto dto) {
		String email = dto.getMem_email();
		number = service.createnumber();
		boolean result=true;
		int a = memberService.searchmember(dto);
		if(a>0) {
			result = false;
			
		} else { 
		service.sendHTMLEmail(email,number);
		System.out.println(number + "난수");
		}
		return result;
	}
	
	//비밀번호 찾기 컨트롤러
	@PostMapping("/emailpwd")
	public boolean emailpwd(@RequestBody MemberDto dto) {
		String email = dto.getMem_email();
		number = service.createnumber();
		System.out.println(dto.getMem_email());
		boolean result=false;
		int a = memberService.searchmember(dto);
		System.out.println(a);
		if(a>0) {
			result = true;
			service.sendHTMLEmail(email,number);
			System.out.println(number + "난수");
			
		} else { 
			result = false;
		}
		return result;
	}
	
	
	//마이페이지 비밀번호 일치여부 컨트롤러
	@PostMapping("/mypwdChk")
	public boolean mypwdChk(@RequestBody MemberDto dto) {
		System.out.println(dto.getMem_pwd());
		//비밀번호 일치여부 확인
		dto.setMem_email(securityservice.userId());
		return memberService.pwdcheck(dto);
	}
	
	
	//마이페이지 비밀번호 변경 컨트롤러
	@PostMapping("/mypwdChg")
	public boolean mypwdChg(@RequestBody MemberDto dto) {
		dto.setMem_email(securityservice.userId());
		System.out.println(dto.getMem_pwd() + "mem_pwd");
		memberService.randompwd(dto);
		return true;
	}
	
	@PostMapping("/emailOk")
	public boolean emailOk(@RequestBody MemberDto dto) {
		System.out.println(dto.getMem_email()+"인증번호 값");
		boolean result = number.equals(dto.getMem_email());
		System.out.println(result +" : boolean");
		return result;
	}
	
	
	
	//비밀번호 난수생성이후 메일발송
	@PostMapping("/emailSendPwd")
	public void emailSednPwd(@RequestBody MemberDto dto) {
		
		//10자리 난수생성
		number=service.createnumberpwd();
		//dto에 pwd값으로 저장
		dto.setMem_pwd(number);
		System.out.println(number+"임시 비밀번호값");
		
		//db에 해당pwd 시큐리티토큰으로 저장
		memberService.randompwd(dto);
		
		//해당값 메일전송
		service.sendHTMLEmailpwd(dto.getMem_email(),number);
		
	}
	
	
}
