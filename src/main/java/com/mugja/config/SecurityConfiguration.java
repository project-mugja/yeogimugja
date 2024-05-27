package com.mugja.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		
		return new BCryptPasswordEncoder();
	}
	
	
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
       
		http.authorizeHttpRequests((auth) -> auth
				.requestMatchers("/","/mugja/login,/mugja/loginaction","/mugja/join","/mugja/create","/mugja/email","/mugja/emailOk",
						"/mugja/pwdfind","/mugja/emailpwd","/mugja/pwdchgemail","/mugja/emailSendPwd").permitAll()
				.requestMatchers("/mugja/admin").hasRole("ADMIN")
				.requestMatchers("/mugja/**").hasAnyRole("ADMIN","USER")
				.anyRequest().permitAll()
				);
		http
			.formLogin((auth)->auth.loginPage("/mugja/login")
					.loginProcessingUrl("/mugja/loginaction").defaultSuccessUrl("/")
					.permitAll());
	
		
		http
        // 로그아웃 설정
        .logout(auth -> auth
                // 로그아웃 요청을 처리할 URL 설정
                .logoutUrl("/mugja/logout")
                // 로그아웃 성공 시 리다이렉트할 URL 설정
                .logoutSuccessUrl("/mugja/login")
                // 로그아웃 성공 핸들러 추가 (리다이렉션 처리)
                .logoutSuccessHandler((request, response, authentication) ->
                        response.sendRedirect("/mugja/login"))

        );
		
		http .csrf((auth)->auth.disable());

        
        return http.build();
    }
}
