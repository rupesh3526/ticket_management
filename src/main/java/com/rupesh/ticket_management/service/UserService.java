package com.rupesh.ticket_management.service;


import com.rupesh.ticket_management.dto.UserDTO;
import com.rupesh.ticket_management.dto.response.UserResponseDTO;

public interface UserService {

	void addUser(UserDTO user);

	UserResponseDTO getUser(Long Id);

	UserResponseDTO updateRole(Long useId , Integer roleId );

}
