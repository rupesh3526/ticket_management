package com.rupesh.ticket_management.security;

import com.rupesh.ticket_management.entity.User;
import com.rupesh.ticket_management.repository.UserRepo;

public interface CreaterProvider {
	User getCreater(UserRepo userRepo, Long id);

}
