package com.rupesh.ticket_management.service.impl;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.rupesh.ticket_management.exception.MaximumLimitReachedException;
import com.rupesh.ticket_management.service.RedisService;
@Service
public class RedisServiceImpl implements RedisService {
	
	@Autowired
	private RedisTemplate<String,Integer> redisTemplate;
	
	public void loginAttempts(String username) {
	    Integer raw = redisTemplate.opsForValue().get(username);

	    if (raw == null) {
	        
	        redisTemplate.opsForValue().set(username, 1,15, TimeUnit.MINUTES);
	    } else {
	         
	        if (raw >= 5) {
	            throw new MaximumLimitReachedException("Try After Some Time");
	        } else {
	            redisTemplate.opsForValue().increment(username);
	        }
	    }
	}

	public void resetLoginAttempts(String username) {
	    redisTemplate.delete(username);
	}
	
}
