package com.rupesh.ticket_management.service.impl;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.rupesh.ticket_management.entity.Role;
import com.rupesh.ticket_management.entity.User;
import com.rupesh.ticket_management.entityDto.UserDTO;
import com.rupesh.ticket_management.entityDto.response.UserResponseDTO;
import com.rupesh.ticket_management.repository.RoleRepo;
import com.rupesh.ticket_management.repository.UserRepo;
import com.rupesh.ticket_management.service.UserService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserServiceImp implements UserService {
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private RoleRepo roleRepo;

	@Override
	@Transactional
	public ResponseEntity<String>  addUser(UserDTO userDTO) {

		Role role = roleRepo.findById(userDTO.getRoleId()).orElseThrow();
		User user = new User();
		user.setName(userDTO.getName());
		user.setEmail(userDTO.getEmail());
		user.setRole(role);
		user.setPassword(userDTO.getPassword());
		this.userRepo.save(user);
		return ResponseEntity.status(HttpStatus.CREATED).body("User Successfully addded"); 
	}

	@Override
	public ResponseEntity<UserResponseDTO> getUser(int Id) {
		User user = userRepo.findById(Id).orElseThrow();
		UserResponseDTO userResponse = mapper.map(user, UserResponseDTO.class);
	userResponse.setRole(user.getRole().getName());
		return ResponseEntity.ok(userResponse);
	}
	
	

}
