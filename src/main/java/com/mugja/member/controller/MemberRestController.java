package com.mugja.member.controller;

import com.mugja.jwt.JwtUtils;
import com.mugja.member.dto.LoginRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import com.mugja.member.dto.MemberDto;
import com.mugja.member.service.MailService;
import com.mugja.member.service.MemberServiceImpl;
import com.mugja.member.service.SecurityService;

import java.io.IOException;
import java.util.Collection;

@RestController
@RequestMapping("/mugja")
public class MemberRestController {
	
	@Autowired
	private MailService service;

	@Autowired
	private MemberServiceImpl memberService;
	@Autowired
	private SecurityService securityservice;

	@Autowired
	private JwtUtils jwtUtils;

	private AuthenticationManager authenticationManager;

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

	
	//로그인 버튼 누르면 이 메서드
	@RequestMapping(value="/loginaction",method = {RequestMethod.POST})
	public ResponseEntity doLogin(@RequestBody LoginRequest req, HttpServletResponse res){

		System.out.println("doLogin");

		System.out.println("name : "+req.getUsername()+ " password : "+req.getPassword());

		// 사용자 인증
		// 커스텀 유저 디테일 서비스

		System.out.println("auth : "+authentication.getName());

		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

		authorities.forEach(authority -> {
			if (authority.getAuthority().equals("ROLE_ADMIN")) {
				try {
					res.sendRedirect("/mugja/admin");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});

		// 인증이 성공하면 JWT 토큰 생성
		String token = jwtUtils.createToken(memberService.getMemId()+"");

		// 토큰을 응답에 포함하여 반환
		return ResponseEntity.ok(token);
	}
	

}
