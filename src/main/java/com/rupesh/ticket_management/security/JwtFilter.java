package com.rupesh.ticket_management.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {
	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String path = request.getServletPath();
		String authHeader = request.getHeader("Authorization");

		if (path.startsWith("/auth/")) {
			filterChain.doFilter(request, response);
			return;
		}

		if (authHeader != null && authHeader.startsWith("Bearer ")) {
			String token = authHeader.substring(7);
			if (jwtUtil.isTokenValid(token)) {
				String username = jwtUtil.extractUsername(token);
				UserDetails userDetails = userDetailsService.loadUserByUsername(username);

				if (SecurityContextHolder.getContext().getAuthentication() == null) {

					UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails,
							null, userDetails.getAuthorities());

					SecurityContextHolder.getContext().setAuthentication(auth);

				}
			}

			
		}
		filterChain.doFilter(request, response);
	}
}
