package com.mugja.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mugja.member.dto.MemberDto;
import com.mugja.member.service.MemberServiceImpl;

@Controller
@RequestMapping("/mugja")
@ComponentScan
//@MapperScan("com.mugja.member.mapper")
public class MemberController {
	
	
	@Autowired
	private MemberServiceImpl service;
	


	public MemberController(MemberServiceImpl service) {
		this.service = service;
	}

	@RequestMapping(value="/login",method = {RequestMethod.GET,RequestMethod.POST})
	public String login() {
		return "/view/login";
	}
	@RequestMapping(value="/admin",method = {RequestMethod.GET,RequestMethod.POST})
	public String loginadmin() {
		System.out.println("admin");
		return "/view/admin";
	}
	
	@RequestMapping(value="/loginaction",method = {RequestMethod.GET,RequestMethod.POST})
	public String logindo() {
		System.out.println("로그인시도");
		return "/view/loginOk";
	}
	
	@RequestMapping(value="/join",method = {RequestMethod.GET,RequestMethod.POST})
	public String join(MemberDto dto) {
		
		return "/view/join";
	}
	
	@RequestMapping(value="/create",method = {RequestMethod.GET,RequestMethod.POST})
	public String create(MemberDto dto) {
		System.out.println("회원가입시도");
		System.out.println(dto);
		service.createmember(dto);
		return "redirect:/mugja/login";
	}
	
	
	
}
