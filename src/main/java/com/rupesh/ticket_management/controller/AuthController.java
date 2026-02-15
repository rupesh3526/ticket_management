package com.rupesh.ticket_management.controller;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rupesh.ticket_management.dto.AuthRequest;
import com.rupesh.ticket_management.dto.RefreshRequest;
import com.rupesh.ticket_management.dto.UserDTO;
import com.rupesh.ticket_management.security.JwtUtil;
import com.rupesh.ticket_management.service.AuthService;
import com.rupesh.ticket_management.service.UserService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;

@RestController
@RequestMapping("/auth")
public class AuthController {
	private static Logger logger = LoggerFactory.getLogger(AuthController.class);
	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	private UserService userService;
	@Autowired
	private UserDetailsService userDetailService;
	@Autowired
	private AuthService authService;

	@PostMapping("/login")
	public ResponseEntity<?> login(@Valid @RequestBody AuthRequest request) {
		logger.debug("Login attempt for username={}", request.getUsername());
		return ResponseEntity.ok(authService.login(request));

	}

	@PostMapping("/refresh")
	public ResponseEntity<?> refresh(@Valid @RequestBody RefreshRequest refreshToken) {

		if (!jwtUtil.isTokenValid(refreshToken.getRefreshToken())) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}

		logger.debug("Attempting to generate new token");
		

		return ResponseEntity.ok(authService.refreshJwtToken(refreshToken));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> signUp(@RequestBody UserDTO user) {
		userService.addUser(user);
		return ResponseEntity.ok().build();
	}

}
