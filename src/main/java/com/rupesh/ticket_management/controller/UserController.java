package com.rupesh.ticket_management.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rupesh.ticket_management.entityDto.UserDTO;
import com.rupesh.ticket_management.entityDto.response.UserResponseDTO;
import com.rupesh.ticket_management.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;

	@GetMapping("ding")
	public String dong() {
		return "Dong";
	}
	

	@GetMapping("/whoami")
	public ResponseEntity<?> whoAmI() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Map<String, Object> map = new LinkedHashMap<>();
		map.put("Username", auth.getName());
		map.put("Role", auth.getAuthorities());
		map.put("Authorize?", auth.isAuthenticated());
		return ResponseEntity.ok(map);

	}
	
	@PostMapping("createUser")
	public ResponseEntity<String> createUser(@Valid @RequestBody UserDTO userDto) {

		userService.addUser(userDto);
		return ResponseEntity.status(HttpStatus.CREATED).body("User Successfully addded");
	}

	@GetMapping("/getUser/{Id}")
	public ResponseEntity<UserResponseDTO> getUser(@Valid @PathVariable Long Id) {
		UserResponseDTO	userResponse =userService.getUser(Id);
		return		ResponseEntity.status(HttpStatus.OK).body(userResponse);
	}

}