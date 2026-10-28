package com.sadiq.fintech.auth.security;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.sadiq.fintech.auth.repository.UserRepository;
import com.sadiq.fintech.auth.entity.User;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	
	private JwtService jwtService;
	private UserRepository userRepository;
	
	public JwtAuthenticationFilter(JwtService jwtService, UserRepository userRepository) {
		this.jwtService = jwtService;
		this.userRepository = userRepository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String authHeader = request.getHeader("Authorization");
//		System.out.println("JWT FILTER HIT");
//		System.out.println("AUTHORIZATION header present: "+ (authHeader !=null));


		if(authHeader == null || !authHeader.startsWith("Bearer ")) {
			filterChain.doFilter(request, response);
			return;
		}
		
		String token = authHeader.substring(7);
		
		if (!jwtService.isTokenValid(token)) {
		    response.sendError(
		        HttpServletResponse.SC_UNAUTHORIZED,
		        "Invalid or expired token"
		    );
		    return;
		}
		
		String  username = jwtService.extractUsername(token);
		

		
		if(SecurityContextHolder.getContext().getAuthentication() == null) {
			User user = userRepository.findByUsername(username)
					.orElse(null);
			
			if(user != null && "Active".equalsIgnoreCase(user.getStatus()) && user.getRole() != null) {
				var authorities = List.of(
						new SimpleGrantedAuthority("ROLE_" + user.getRole())
						
						);
				
				 var authentication =
			                new UsernamePasswordAuthenticationToken(
			                        username,
			                        null,
			                        authorities
			                );
				 
				 SecurityContextHolder.getContext()
	                .setAuthentication(authentication);
					
//					System.out.println("Authenticated user: "+ username);
				
			}
			
		}
		
		
		filterChain.doFilter(request, response);
		
		
		
	}
	
	

}
