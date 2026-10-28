package com.sadiq.fintech.auth.security;

import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import jakarta.servlet.http.HttpServletResponse;

@Configuration
@ConfigurationPropertiesScan
public class SecurityConfig {
	
	private JwtAuthenticationFilter jwtAuthenticationFilter;
	
	   public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
		   this.jwtAuthenticationFilter = jwtAuthenticationFilter;
	    	
	    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    	

        http
                .csrf(csrf->csrf.disable())
                .exceptionHandling(exception -> exception
                		.authenticationEntryPoint((request, 
                				response, authException) -> 
                response.sendError(
                		HttpServletResponse.SC_UNAUTHORIZED, "Authentication required"
                		)
                	)
                )
                
                .sessionManagement(session-> session
                		.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                		)
                
                .authorizeHttpRequests(auth->auth
                        .requestMatchers("/api/register", "/api/login").permitAll()
                        .anyRequest().authenticated()
                )
                 .addFilterBefore(
                		 jwtAuthenticationFilter,
                             UsernamePasswordAuthenticationFilter.class
                            );

        return  http.build();
    }
    
 
 
    
}
