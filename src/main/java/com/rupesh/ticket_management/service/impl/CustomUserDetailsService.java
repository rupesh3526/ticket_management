package com.rupesh.ticket_management.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.rupesh.ticket_management.dto.UserRedisDTO;
import com.rupesh.ticket_management.entity.Users;
import com.rupesh.ticket_management.repository.UserRepo;
import com.rupesh.ticket_management.service.RedisService;

@Service

public class CustomUserDetailsService implements UserDetailsService {
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private RedisService redisService;
	private static Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);

	public Users loadUser(String username) {
		System.err.println("user not " + "from db");
		Users user = userRepo.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException(username));
		System.err.println("user" + "from db");
		return user;
	}

	@Override
	public UserDetails loadUserByUsername(String username) {
		long start2 = System.currentTimeMillis(); 
		UserRedisDTO userDTO  =  redisService.loadUserFromRedis(username);
		logger.error("Attempting to load user by username={}", username);

		
		  if (userDTO == null) {
		  
		  Users user = loadUser(username); userDTO = new UserRedisDTO();
		  
		  userDTO.setUsername(user.getEmail());
		  userDTO.setPassword(user.getPassword());
		  userDTO.setRole(user.getRole().getName());
		  redisService.saveUserInRedis(userDTO); }
		  
		  System.err.println(
					 "load user redis + db took " + (System.currentTimeMillis() - start2) + " ms" );
		 
		/*
		 * long start2 = System.currentTimeMillis(); Users user = loadUser(username);
		 * userDTO = new UserRedisDTO();
		 * 
		 * userDTO.setUsername(user.getEmail());
		 * userDTO.setPassword(user.getPassword());
		 * userDTO.setRole(user.getRole().getName());
		 * //redisService.saveUserInRedis(userDTO); System.err.println(
		 * "load user  took " + (System.currentTimeMillis() - start2) + " ms" );
		 */
		
		logger.error("loaded user by username from db={}", userDTO.getUsername());

		long start = System.currentTimeMillis();
		UserDetails userDetail = User.withUsername(userDTO.getUsername()).password(userDTO.getPassword())
				.roles(userDTO.getRole()).build();
		System.err.println(
			    "Authentication took "
			    + (System.currentTimeMillis() - start)
			    + " ms"
			);
		logger.debug("UserDetails successfully built for username={}", username);
		return userDetail;
	}

}
