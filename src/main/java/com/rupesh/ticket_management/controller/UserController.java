package com.rupesh.ticket_management.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

	@PostMapping("createUser")
	public ResponseEntity<String> createUser(@Valid @RequestBody UserDTO userDto) {
		

		userService.addUser(userDto);
		return ResponseEntity.ok("Succesfully user Created");

	}

	@GetMapping("/getUser/{Id}")
	public ResponseEntity<UserResponseDTO> getUser(@Valid @PathVariable Long Id) {
		return userService.getUser(Id);
	}

	}