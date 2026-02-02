package com.rupesh.ticket_management.security;

import org.springframework.stereotype.Component;

import com.rupesh.ticket_management.Entity.User;
import com.rupesh.ticket_management.Jpa.UserRepo;
@Component
public class CreaterProviderImp implements  CreaterProvider {

	@Override
	public User getCreater(UserRepo userRepo,int id) {
		
		
		return  userRepo.findById(id).orElseThrow();
	}

}
