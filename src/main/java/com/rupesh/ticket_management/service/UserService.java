package com.rupesh.ticket_management.service;

import org.springframework.http.ResponseEntity;

import com.rupesh.ticket_management.entityDto.UserDTO;
import com.rupesh.ticket_management.entityDto.response.UserResponseDTO;


public interface UserService {
	
	ResponseEntity<String> addUser(UserDTO user);
	ResponseEntity<UserResponseDTO> getUser(Long Id);
	


}
