package com.mugja.member.controller;

import com.mugja.jwt.JwtUtils;
import com.mugja.member.dto.LoginRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.mugja.jwt.JwtUtils;
import com.mugja.member.dto.LoginRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.origin.SystemEnvironmentOrigin;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mugja.member.dto.MemberDto;
import com.mugja.member.service.MemberServiceImpl;

@Controller
@RequestMapping("/mugja")
@ComponentScan
public class MemberController {


	@Autowired
	private MemberServiceImpl service;

	@Autowired
	private JwtUtils jwtUtils;

	@Autowired
	private UserDetailsService userDetailsService;

	private AuthenticationManager authenticationManager;




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




	@RequestMapping(value="/join",method = {RequestMethod.GET,RequestMethod.POST})
	public String join(MemberDto dto) {
		return "/view/join";
	}

	@RequestMapping(value="/mypage",method = {RequestMethod.GET,RequestMethod.POST})
	public String mypage(MemberDto dto) {
		return "/view/mypage";
	}

	@RequestMapping(value="/create",method = {RequestMethod.GET,RequestMethod.POST})
	public String create(MemberDto dto) {
		dto.setMem_email(dto.getMem_email_hidden());
		service.createmember(dto);
		return "redirect:/mugja/login";
	}
	@RequestMapping(value="/pwdfind",method = {RequestMethod.GET,RequestMethod.POST})
	public String pwdfind() {
		return "/view/pwdfind";
	}

	@RequestMapping(value="/pwdchgemail",method = {RequestMethod.GET,RequestMethod.POST})
	public String pwdchg() {
		return "/view/pwdchgemail";
	}


	@RequestMapping(value="/myPwdChg",method = {RequestMethod.GET,RequestMethod.POST})
	public String myPwdChg() {
		return "/view/myPwdChg";
	}



}
