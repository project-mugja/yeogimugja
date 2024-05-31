package com.mugja.config;

import com.mugja.jwt.JwtUtils;
import jakarta.servlet.http.Cookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Collection;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final JwtUtils jwtUtils;

    @Autowired
    public CustomAuthenticationSuccessHandler(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        System.out.println("successhandler");
//        String token = jwtUtils.createToken(authentication.getName());
//
//        System.out.println("============================");
//        System.out.println("request " + request.toString());
//        System.out.println("on Login Success : "+token);
//
//        ResponseCookie cookie = ResponseCookie.from("token",token)
//                .httpOnly(true)
//                .path("/")
//                .secure(true)
//                .sameSite("None")
//                .domain("main--mugja.netlify.app")
//                .build();
//
//        System.out.println("cookie : "+cookie.getName());
//        System.out.println("sameSite : "+cookie.getSameSite());
//        response.addHeader("Set-Cookie",cookie.toString());
//
//
//        System.out.println("header : "+response.getHeaderNames());
//        System.out.println("cookie name : "+cookie.getName()+"  cookie value : "+cookie.getValue()+"");

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        authorities.forEach(authority -> {
            if (authority.getAuthority().equals("ROLE_ADMIN")) {
                try {
                    response.sendRedirect("/mugja/admin");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}