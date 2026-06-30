package com.rupesh.ticket_management.service.impl;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.rupesh.ticket_management.dto.UserRedisDTO;
import com.rupesh.ticket_management.entity.Users;
import com.rupesh.ticket_management.exception.MaximumLimitReachedException;
import com.rupesh.ticket_management.service.RedisService;
@Service
public class RedisServiceImpl implements RedisService {
	
	@Autowired
	private RedisTemplate<String,Object> redisTemplate;
	
	public void loginAttempts(String username) {
	    Integer raw = (Integer) redisTemplate.opsForValue().get("LoginCounter"+username );

	    if (raw == null) {
	        
	        redisTemplate.opsForValue().set("LoginCounter"+username, 1,15, TimeUnit.MINUTES);
	    } else {
	         
	        if (raw >= 5) {
	            throw new MaximumLimitReachedException("Try After Some Time");
	        } else {
	            redisTemplate.opsForValue().increment("LoginCounter"+username);
	        }
	    }
	}

	public void resetLoginAttempts(String username) {
	    redisTemplate.delete("LoginCounter"+username);
	}
	
	public UserRedisDTO loadUserFromRedis(String username ) {
	return (UserRedisDTO)	redisTemplate.opsForValue().get(username);
		}

	@Override
	public void saveUserInRedis(UserRedisDTO user) {
		redisTemplate.opsForValue().set(user.getUsername(), user,15,TimeUnit.MINUTES);
		
		
	}
	
}
