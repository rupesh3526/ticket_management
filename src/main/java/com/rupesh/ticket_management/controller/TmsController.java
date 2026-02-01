package com.rupesh.ticket_management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rupesh.ticket_management.EntityDTO.UserDTO;
import com.rupesh.ticket_management.service.UserService;

import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;

@RestController
public class TmsController {
	@Autowired
	private UserService userService;
	@GetMapping("ding")
	public String dong() {
		return "Dong";
	}
	
	@PostMapping("createUser")
	public void createUser(@Valid @RequestBody UserDTO userDto) {
		System.out.println(userDto.getEmail());
		
		userService.addUser(userDto);
		
		
	}
	
	

}
