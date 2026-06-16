package com.rupesh.ticket_management;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.rupesh.ticket_management.dto.UserDTO;
import com.rupesh.ticket_management.entity.Users;
import com.rupesh.ticket_management.repository.RoleRepo;
import com.rupesh.ticket_management.repository.UserRepo;
import com.rupesh.ticket_management.service.RedisService;
import com.rupesh.ticket_management.service.UserService;


@SpringBootTest
@Rollback(false)
class TicketManagementApplicationTests {
	@Autowired
   private RedisService redisService;

	@Test
	void Test() {
		redisService.loginAttempts("Test");
		System.err.println("Succes");
	}
}
