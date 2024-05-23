package com.mugja.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mugja.member.dto.MemberDto;
import com.mugja.member.service.MailService;
import com.mugja.member.service.MemberServiceImpl;

@RestController
@RequestMapping("/mugja")
public class MemberRestController {
	
	@Autowired
	private MailService service;
	
	@Autowired
	private MemberServiceImpl memberService;
	
	private String number="";
	
	
	@PostMapping("/email")
	public boolean email(@RequestBody MemberDto dto) {
		String email = dto.getMem_email();
		boolean result=true;
		int a = memberService.searchmember(dto);
		if(a>0) {
			result = false;
			
		} else { 
		number = service.sendHTMLEmail(email);
		System.out.println(number + "난수");
		}
		return result;
	}
	
	@PostMapping("/emailOk")
	public boolean emailOk(@RequestBody MemberDto dto) {
		System.out.println(dto.getMem_email()+"인증번호 값");
		boolean result = number.equals(dto.getMem_email());
		System.out.println(result +" : boolean");
		return result;
	}
	
	
}
