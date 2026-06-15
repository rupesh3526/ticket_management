package com.rupesh.ticket_management.service;


import java.util.List;

import com.rupesh.ticket_management.dto.UserDTO;
import com.rupesh.ticket_management.dto.response.UserResponseDTO;

public interface UserService {

	void addUser(UserDTO user);

	UserResponseDTO getUser(Long Id);

	UserResponseDTO updateRole(Long useId , Integer roleId );
	
	List<UserResponseDTO> getAllUsers();
	String updateUser(Long id, UserDTO userDTO);
	String deleteUser(Long id);

}
