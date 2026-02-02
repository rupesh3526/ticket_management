package com.rupesh.ticket_management;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.rupesh.ticket_management.entity.User;
import com.rupesh.ticket_management.entityDto.UserDTO;
import com.rupesh.ticket_management.repository.RoleRepo;
import com.rupesh.ticket_management.repository.UserRepo;
import com.rupesh.ticket_management.service.UserService;

import jakarta.transaction.Transactional;

@SpringBootTest
@Rollback(false)
class TicketManagementApplicationTests {
	@Autowired
    private UserService userService;
	@Autowired
	private RoleRepo roleRepo;

    @Autowired
    private UserRepo userRepo;
    
    @Test
    void testAddUser() {
        // given
        UserDTO userDTO = new UserDTO();
        userDTO.setName("Rupesh4");
        userDTO.setEmail("rupesh4@test.com");
        userDTO.setRoleId(1);

        // when
        userService.addUser(userDTO);

        // then
      User savedUser = userRepo.findAll().get(2);
      System.out.println(savedUser.getName());
       assertNotNull(savedUser.getId());

}}
