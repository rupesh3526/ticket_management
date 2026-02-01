package com.rupesh.ticket_management.service;

import org.springframework.stereotype.Service;

import com.rupesh.ticket_management.Entity.User;
import com.rupesh.ticket_management.EntityDTO.UserDTO;

@Service
public interface UserService {
	
	void addUser(UserDTO user);

}
