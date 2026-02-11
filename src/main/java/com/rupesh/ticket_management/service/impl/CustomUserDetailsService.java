package com.rupesh.ticket_management.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.rupesh.ticket_management.entity.Users;
import com.rupesh.ticket_management.repository.UserRepo;
@Service
public class CustomUserDetailsService  implements UserDetailsService{
@Autowired	
private UserRepo userRepo ;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users user =userRepo.findByEmail(username).orElseThrow(()-> new UsernameNotFoundException(username));
		System.out.println("Indside load user by username");
		 UserDetails userDetail = User.withUsername(user.getEmail())
				 .password(user.getPassword())
				 .roles(user.getRole().getName())
				 .build();
		return userDetail;
	}

}
