package com.rupesh.ticket_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rupesh.ticket_management.entity.Role;

public interface RoleRepo extends JpaRepository<Role, Integer>{
	

}
