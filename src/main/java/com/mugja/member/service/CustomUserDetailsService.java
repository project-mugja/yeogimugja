package com.mugja.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mugja.member.dto.CustomUserDetails;
import com.mugja.member.dto.MemberDto;
import com.mugja.member.mapper.MemberMapper;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private MemberMapper mapper;

	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("username: " + username);
		MemberDto dto = mapper.findByEmail(username);
		
		if(dto!=null) {
			System.out.println("dto: " + dto.getMem_pwd());
			return new CustomUserDetails(dto);
		}
		return null;
		
	}

}
