package com.rupesh.ticket_management.security;

import com.rupesh.ticket_management.Entity.User;
import com.rupesh.ticket_management.Jpa.UserRepo;

public interface CreaterProvider {
	User getCreater(UserRepo userRepo, int id);

}
