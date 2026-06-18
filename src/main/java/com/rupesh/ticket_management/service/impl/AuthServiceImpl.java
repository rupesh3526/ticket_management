package com.rupesh.ticket_management.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException.Unauthorized;

import com.rupesh.ticket_management.dto.AuthRequest;
import com.rupesh.ticket_management.dto.RefreshRequest;
import com.rupesh.ticket_management.dto.response.AuthResponse;
import com.rupesh.ticket_management.entity.UserSession;
import com.rupesh.ticket_management.entity.Users;
import com.rupesh.ticket_management.exception.UserNotFoundException;
import com.rupesh.ticket_management.repository.UserRepo;
import com.rupesh.ticket_management.security.JwtUtil;
import com.rupesh.ticket_management.service.AuthService;
import com.rupesh.ticket_management.service.LoginSessionService;
import com.rupesh.ticket_management.service.RedisService;

@Service
public class AuthServiceImpl implements AuthService {
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	private LoginSessionService loginSessionService;
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private RedisService redisService;
	private static final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);

	@Override
	public AuthResponse login(AuthRequest request) {

		//redisService.loginAttempts(request.getUsername());
		long start = System.currentTimeMillis();
		Authentication auth = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		System.err.println(
			    "AuthenticationManger took "
			    + (System.currentTimeMillis() - start)
			    + " ms"
			);
		UserDetails userDetail = (UserDetails) auth.getPrincipal();

		//redisService.resetLoginAttempts(request.getUsername());
		long start2 = System.currentTimeMillis();
		logger.info("User authenticated , Generating tokens");
		String token = jwtUtil.generateToken(userDetail);
		String refreshToken = jwtUtil.generateRefreshToken(userDetail);
		System.err.println(
			    "Token genration  took "
			    + (System.currentTimeMillis() - start2)
			    + " ms"
			);

		return new AuthResponse(token, refreshToken, "Login Successful");

	}

	public AuthResponse refreshJwtToken(RefreshRequest refreshToken) {
		UserSession userSession = loginSessionService.validateSession(refreshToken.getRefreshToken());
		loginSessionService.revokeSession();
		logger.info("Token validated and Revoked old Session");
		String name = jwtUtil.extractUsername(refreshToken.getRefreshToken());
		Users user = userRepo.findByEmail(name).orElseThrow();
		UserDetails userDetails = userDetailsService.loadUserByUsername(name);
		String newAccessToken = jwtUtil.generateToken(userDetails);
		String newRefreshToken = jwtUtil.generateRefreshToken(userDetails);
		logger.info("Tokens Generated");

		loginSessionService.saveSession(user, newRefreshToken);

		return new AuthResponse(newAccessToken, newRefreshToken, "New accessToken Generated");
	}
}