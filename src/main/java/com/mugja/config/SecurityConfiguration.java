package com.mugja.config;


import com.mugja.jwt.JwtAuthFilter;
import com.mugja.jwt.JwtUtils;
import com.mugja.jwt.LoginAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
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

		@Autowired
		public SecurityConfiguration(JwtUtils jwtUtils, UserDetailsService userDetailsService, CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler) {
			this.jwtUtils = jwtUtils;
			this.userDetailsService = userDetailsService;
		}

		@Bean
		public BCryptPasswordEncoder bCryptPasswordEncoder() {
			return new BCryptPasswordEncoder();
		}


	@Bean
	public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
		AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
		authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
		return authenticationManagerBuilder.build();
	}

	    @Bean
	    public SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManager authenticationManager) throws Exception {

			http.csrf(AbstractHttpConfigurer::disable)
					.formLogin(AbstractHttpConfigurer::disable)
					.httpBasic(AbstractHttpConfigurer::disable)
					.authorizeHttpRequests((auth) -> auth
						.requestMatchers("/mugja/main", "/mugja/login", "/mugja/loginaction", "/mugja/join", "/mugja/create", "/mugja/email", "/mugja/emailOk",
								"/mugja/pwdfind", "/mugja/emailpwd", "/mugja/pwdchgemail", "/mugja/emailSendPwd").permitAll()
						.requestMatchers("/mugja/admin/**").hasRole("ADMIN")
						.requestMatchers("/mugja/**").hasAnyRole("ADMIN","USER")
						.requestMatchers("/api","/jwt").authenticated()
						.anyRequest().permitAll()
	        ).addFilterAt(
							this.abstractAuthenticationProcessingFilter(authenticationManager),
							UsernamePasswordAuthenticationFilter.class)
			.logout(auth -> auth
			.logoutUrl("/mugja/logout")
			.logoutSuccessUrl("/mugja/login")
			.logoutSuccessHandler((request, response, authentication) ->
					response.sendRedirect("/mugja/login"))
	        )
			.cors(cors -> cors.configurationSource(corsConfigurationSource()))
			.addFilterBefore(new JwtAuthFilter(jwtUtils,userDetailsService), UsernamePasswordAuthenticationFilter.class);

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

	public AbstractAuthenticationProcessingFilter abstractAuthenticationProcessingFilter(final AuthenticationManager authenticationManager) {
		LoginAuthenticationFilter filter = new LoginAuthenticationFilter("/mugja/loginaction", authenticationManager,jwtUtils);
		filter.setAuthenticationSuccessHandler(new CustomAuthenticationSuccessHandler(jwtUtils));
		return filter;
	}

}

