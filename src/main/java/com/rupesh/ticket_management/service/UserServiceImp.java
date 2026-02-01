package com.rupesh.ticket_management.service;

import java.lang.System.Logger;
import java.lang.System.Logger.Level;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rupesh.ticket_management.Entity.Role;
import com.rupesh.ticket_management.Entity.User;
import com.rupesh.ticket_management.EntityDTO.UserDTO;
import com.rupesh.ticket_management.Jpa.RoleRepo;
import com.rupesh.ticket_management.Jpa.UserRepo;

import jakarta.transaction.Transactional;
@Service
@Transactional
public class UserServiceImp implements UserService {
	@Autowired
	private UserRepo userRepo ;
	@Autowired
	private ModelMapper userMapper;
	@Autowired
	private RoleRepo roleRepo;

	@Override
	public void addUser(UserDTO userDTO ) {
		System.out.println("addUser() called");
	Role role	= roleRepo.findById(userDTO.getRoleId()).orElseThrow();
		
		

	User user =	this.userMapper.map(userDTO,User.class);
	user.setRole(role);
	user.setId(0);                 // ensure INSERT
    user.setRole(role);
   // user.setCreatedTicket(null);   // avoid accidental merge state
   // user.setAssignedTicket(null);
	
		System.out.println(user.getId());
		
		this.userRepo.save(user);
	
		
	}
	

}
