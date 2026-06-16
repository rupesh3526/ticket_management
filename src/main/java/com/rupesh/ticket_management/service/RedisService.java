package com.rupesh.ticket_management.service;

public interface RedisService {
void loginAttempts(String username);
void resetLoginAttempts(String username);
}
