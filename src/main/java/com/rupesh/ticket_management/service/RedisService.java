package com.rupesh.ticket_management.service;

import com.rupesh.ticket_management.dto.UserRedisDTO;
import com.rupesh.ticket_management.entity.Users;

public interface RedisService {
void loginAttempts(String username);
void resetLoginAttempts(String username);
UserRedisDTO loadUserFromRedis(String username );
void saveUserInRedis(UserRedisDTO user );
}
