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
				.requestMatchers("/","/mugja/login,/mugja/loginaction","/mugja/join","/mugja/create","/mugja/email","/mugja/emailOk").permitAll()
				.requestMatchers("/mugja/admin").hasRole("ADMIN")
				.requestMatchers("/mugja/**").hasAnyRole("ADMIN","USER")
				.anyRequest().permitAll()
				);
		http
			.formLogin((auth)->auth.loginPage("/mugja/login")
					.loginProcessingUrl("/mugja/loginaction").defaultSuccessUrl("/")
					.permitAll());
		
		http .csrf((auth)->auth.disable());

        
        return http.build();
    }
}
