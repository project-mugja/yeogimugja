package com.mugja.config;


import com.mugja.jwt.JwtAuthFilter;
import com.mugja.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

 	private final JwtUtils jwtUtils;
	private final UserDetailsService userDetailsService;
	 private final CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

	    @Autowired
	    public SecurityConfiguration(JwtUtils jwtUtils, UserDetailsService userDetailsService, CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler) {
            this.jwtUtils = jwtUtils;
            this.userDetailsService = userDetailsService;
            this.customAuthenticationSuccessHandler = customAuthenticationSuccessHandler;
	    }

	    @Bean
	    public BCryptPasswordEncoder bCryptPasswordEncoder() {
	        return new BCryptPasswordEncoder();
	    }

	    @Bean
	    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	        http.authorizeHttpRequests((auth) -> auth
	                .requestMatchers("/mugja/main", "/mugja/login", "/mugja/loginaction", "/mugja/join", "/mugja/create", "/mugja/email", "/mugja/emailOk",
	                        "/mugja/pwdfind", "/mugja/emailpwd", "/mugja/pwdchgemail", "/mugja/emailSendPwd").permitAll()
	                .requestMatchers("/mugja/admin/**").hasRole("ADMIN")
	                .requestMatchers("/mugja/**").hasAnyRole("ADMIN","USER")
					.requestMatchers("/api","/jwt").authenticated()
	                .anyRequest().permitAll()
	        );


//	        http.formLogin((auth) -> auth
//	                .loginPage("/mugja/login")
//					.loginProcessingUrl("/mugja/loginaction")
//	                .successHandler(customAuthenticationSuccessHandler)
//	                .permitAll()
//	        );

	        http.logout(auth -> auth
	                .logoutUrl("/mugja/logout")
	                .logoutSuccessUrl("/mugja/login")
	                .logoutSuccessHandler((request, response, authentication) ->
	                        response.sendRedirect("/mugja/login"))
	        );
	        http.csrf((auth) -> auth.disable());


			http.cors(cors -> cors.configurationSource(corsConfigurationSource()));
			http.addFilterBefore(new JwtAuthFilter(jwtUtils,userDetailsService), UsernamePasswordAuthenticationFilter.class);

	        return http.build();
	    }


	private CorsConfigurationSource corsConfigurationSource() {

		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOriginPatterns(Arrays.asList(
				"http://localhost:3000",
				"https://main--mugja.netlify.app/"
		));
		configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
		configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
		configuration.setAllowCredentials(true);
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}

}

