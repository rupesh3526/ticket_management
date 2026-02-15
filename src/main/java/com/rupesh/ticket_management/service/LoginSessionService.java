package com.rupesh.ticket_management.service;

import com.rupesh.ticket_management.entity.UserSession;
import com.rupesh.ticket_management.entity.Users;

public interface LoginSessionService {
	void saveSession(Users user,String token);
	void revokeSession();
	void revokeAllSession();
	void deleteSession();
	UserSession validateSession(String token);

}
