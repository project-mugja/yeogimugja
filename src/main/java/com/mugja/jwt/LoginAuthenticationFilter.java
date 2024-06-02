package com.mugja.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mugja.member.dto.LoginRequest;
import com.mugja.member.service.SecurityService;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LoginAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private final JwtUtils jwtUtils;
    private SecurityService securityService = new SecurityService();

    public LoginAuthenticationFilter(final String defaultFilterProcessesUrl,
                                     final AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
        super(defaultFilterProcessesUrl, authenticationManager);
        this.jwtUtils = jwtUtils;

        System.out.println("defaultFilterProcessesUrl: " + defaultFilterProcessesUrl);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        String method = request.getMethod();
        System.out.println("attemtAuthentication : "+ request.getMethod().toString());
        if (!method.equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }

        ServletInputStream inputStream = request.getInputStream();

        LoginRequestDto loginRequestDto = new ObjectMapper().readValue(inputStream, LoginRequestDto.class);

        return this.getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(
                loginRequestDto.username,
                loginRequestDto.password
        ));


    }
    public record LoginRequestDto(
            String username,
            String password
    ){}

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        System.out.println("in successfulAuthentication : "+ authResult);
        // Authentication 객체를 UserDetails 인스턴스로 캐스팅
        UserDetails userDetails = (UserDetails) authResult.getPrincipal();

        // UserDetails를 포함하는 새로운 SecurityContext 생성
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authResult);
        SecurityContextHolder.setContext(context);

        System.out.println(SecurityContextHolder.getContext().getAuthentication().getName());
        System.out.println("======");
        System.out.println("get userId : "+securityService.userId());
        System.out.println("======");

        // 인증된 사용자 정보 추출
        String username = authResult.getName();

        // JWT 토큰 생성
        String token = jwtUtils.createToken(username);

        // 응답 객체 생성
        Map<String, String> responseMap = new HashMap<>();
        responseMap.put("token", token);

        // JSON 문자열로 변환
        String jsonResponse = new ObjectMapper().writeValueAsString(responseMap);

        System.out.println("jsonResponse : " + jsonResponse);

        // 응답 설정
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonResponse);

        request.getSession(true).setAttribute("SPRING_SECURITY_CONTEXT", context);
    }



    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        // Authentication 실패 시 호출
        System.out.println("in unsuccessfulAuthentication");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write("Authentication Failed: " + failed.getMessage());
    }
}
