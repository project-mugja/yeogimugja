package com.mugja.config;

import java.io.IOException;
import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomAuthenticationHandler implements AuthenticationSuccessHandler,AuthenticationFailureHandler,LogoutSuccessHandler {

	
	@Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        
        authorities.forEach(authority -> {
            if (authority.getAuthority().equals("ROLE_ADMIN")) {
                try {
                    response.sendRedirect("/mugja/admin");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (authority.getAuthority().equals("ROLE_USER")) {
                try {
                    response.sendRedirect("/mugja/main");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		 response.sendRedirect("/mugja/login?error=true");
	}
	
	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		response.sendRedirect("/mugja/login?logout");
	}
	

}
