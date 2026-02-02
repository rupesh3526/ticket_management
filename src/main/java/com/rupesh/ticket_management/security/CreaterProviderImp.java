package com.rupesh.ticket_management.security;

import org.springframework.stereotype.Component;

import com.rupesh.ticket_management.entity.User;
import com.rupesh.ticket_management.repository.UserRepo;
@Component
public class CreaterProviderImp implements  CreaterProvider {

	@Override
	public User getCreater(UserRepo userRepo,int id) {
		
		
		return  userRepo.findById(id).orElseThrow();
	}

}
