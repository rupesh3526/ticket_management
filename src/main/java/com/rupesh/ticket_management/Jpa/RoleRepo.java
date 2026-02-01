package com.rupesh.ticket_management.Jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rupesh.ticket_management.Entity.Role;

public interface RoleRepo extends JpaRepository<Role, Integer>{
	

}
