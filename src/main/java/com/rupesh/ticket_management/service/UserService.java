package com.rupesh.ticket_management.service;


import com.rupesh.ticket_management.entityDto.UserDTO;
import com.rupesh.ticket_management.entityDto.response.UserResponseDTO;

public interface UserService {

	void addUser(UserDTO user);

	UserResponseDTO getUser(Long Id);

	UserResponseDTO updateRole(Long useId , Integer roleId );

}
