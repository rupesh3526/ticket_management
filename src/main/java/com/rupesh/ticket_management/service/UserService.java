package com.rupesh.ticket_management.service;

import org.springframework.http.ResponseEntity;
import com.rupesh.ticket_management.EntityDTO.UserDTO;
import com.rupesh.ticket_management.EntityDTO.UserResponseDTO;


public interface UserService {
	
	ResponseEntity<String> addUser(UserDTO user);
	ResponseEntity<UserResponseDTO> getUser(int Id);
	


}
