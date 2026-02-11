package com.rupesh.ticket_management.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.rupesh.ticket_management.entityDto.AuthRequest;
import com.rupesh.ticket_management.entityDto.RefreshRequest;
import com.rupesh.ticket_management.entityDto.UserDTO;
import com.rupesh.ticket_management.security.JwtUtil;
import com.rupesh.ticket_management.service.UserService;
import com.rupesh.ticket_management.service.impl.UserServiceImp;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;

@RestController
@RequestMapping("/auth")
public class AuthController {
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	private UserService userService;
	@Autowired
	private UserDetailsService userDetailService;

	@PostMapping("/login")
	public ResponseEntity<?> login(@Valid @RequestBody AuthRequest request) {

		System.out.println("Inside /login");
		Authentication auth = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		UserDetails user = (UserDetails) auth.getPrincipal();
		String token = jwtUtil.generateToken(user);
		String refreshToken = jwtUtil.generateRefreshToken(user);

		Map<String, String> tokens = new HashMap<>();
		tokens.put("accessToken", token);
		tokens.put("refreshToken", refreshToken);
		return ResponseEntity.ok(tokens);
	}

	@PostMapping("/refresh")
	public ResponseEntity<?> refresh(@Valid @RequestBody RefreshRequest refreshToken) {

		String username = jwtUtil.extractUsername(refreshToken.getRefreshToken());

		UserDetails user = userDetailService.loadUserByUsername(username);

		if (!jwtUtil.isTokenValid(refreshToken.getRefreshToken())) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}

		String newAccessToken = jwtUtil.generateToken(user);

		Map<String, String> response = new HashMap<>();
		response.put("accessToken", newAccessToken);

		return ResponseEntity.ok(response);
	}

	@PostMapping("/signup")
	public ResponseEntity<?> signUp(@RequestBody UserDTO user) {
		userService.addUser(user);
		return ResponseEntity.ok().build();
	}

}
