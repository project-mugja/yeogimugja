package com.mugja.member.dto;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetails implements UserDetails {
	
	private MemberDto dto;
	
	
	
	public CustomUserDetails(MemberDto dto) {
		this.dto = dto;
	}
	
	@Override
	public String getPassword() {
		return dto.getMem_pwd();
	}
	
	 @Override
	    public Collection<? extends GrantedAuthority> getAuthorities() {

	        Collection<GrantedAuthority> collection = new ArrayList<>();

	        collection.add(new GrantedAuthority() {

	            @Override
	            public String getAuthority() {

	                return dto.getMem_grade();
	            }
	        });      
	        return collection;
	    }

	 
	@Override
	public String getUsername() {
		return dto.getMem_email();
	}
	
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	@Override
	public boolean isEnabled() {
		return true;
	}
}
